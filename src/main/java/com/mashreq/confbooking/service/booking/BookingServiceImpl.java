package com.mashreq.confbooking.service.booking;

import com.mashreq.confbooking.constants.AppConstants;
import com.mashreq.confbooking.converter.BookingDetailsConverter;
import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.exception.errorcode.BookingSystemErrors;
import com.mashreq.confbooking.exception.handler.BookingSystemException;
import com.mashreq.confbooking.exception.handler.RoomNotFoundException;
import com.mashreq.confbooking.model.BookingDetails;
import com.mashreq.confbooking.model.RoomDetails;
import com.mashreq.confbooking.repository.BookingRepository;
import com.mashreq.confbooking.repository.RoomRepository;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.request.queue.BookingQueueRequest;
import com.mashreq.confbooking.response.BookingConfirmationResponse;
import com.mashreq.confbooking.response.BookingResponse;
import com.mashreq.confbooking.service.AbstractRoomService;
import com.mashreq.confbooking.service.queue.QueueService;
import com.mashreq.confbooking.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl extends AbstractRoomService implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    BookingDetailsConverter bookingDetailsConverter;

    @Value("${booking.system.allowed.interval}")
    String systemAllowedInterval;

    @Autowired
    QueueService queueService;


    @Override
    public BookingConfirmationResponse bookConferenceRoom(BookingRequest roomBookingRequestDto) {
        // Validate the time that we received and interval should be 15 mins.
        String startTime = roomBookingRequestDto.getStartTime();
        String endTime = roomBookingRequestDto.getEndTime();

        if(!hasSpecifiedInterval(startTime, endTime,
                Integer.valueOf(systemAllowedInterval))) {
            throw new BookingSystemException(BookingSystemErrors.E_INVALID_TIME.getErrorCode(),
                    BookingSystemErrors.E_INVALID_TIME.getMessage());
        }

        log.info("Start Booking room on date :{}, startTime : {}, endTime :{}",
                roomBookingRequestDto.getBookingDate(), startTime, endTime);

        // Check for ideal roomm with no overlapping time with maintanence & existing booking time...
        Optional<RoomDetails> idealRoomForBooking = roomRepository
                .findByIsActiveAndMaxCapacityGreaterThanEqualOrderByMaxCapacityAsc(true, roomBookingRequestDto.getAttendeesCount())
                .stream()
                .filter(eachRoom -> !hasMaintanenceOverlapping(startTime, endTime, eachRoom)
                        && isRoomAvailable(startTime, endTime, eachRoom.getId()))
                .findFirst();

        if(!idealRoomForBooking.isPresent()) {
            log.info("No rooms available to book for requested time date :{}, startTime : {}, endTime :{}",
                    roomBookingRequestDto.getBookingDate(), startTime, endTime);
            throw new RoomNotFoundException(BookingSystemErrors.E_ROOMS_NOT_AVAILABLE.getErrorCode(),
                    BookingSystemErrors.E_ROOMS_NOT_AVAILABLE.getMessage());
        }

        log.info("pushing the booking request to queue for further processing");
        RoomDetails roomDetails = idealRoomForBooking.get();
        String bookingJson = CommonUtil.toJson(BookingQueueRequest.builder()
                .bookingRequest(roomBookingRequestDto)
                .roomName(roomDetails.getName())
                .roomId(roomDetails.getId())
                .build());

        log.info("Request for booking ==> {}", bookingJson);
        queueService.pushItemToQueue(bookingJson);

        return BookingConfirmationResponse.builder()
                .status(AppConstants.SUCCESS)
                .message(AppConstants.SUCCESS_MESSAGE)
                .roomName(roomDetails.getName())
                .build();
    }


    public BookingResponse processBookingRequest(BookingQueueRequest bookingQueueRequest) {
        try {
            BookingRequest bookingRequest = bookingQueueRequest.getBookingRequest();
            boolean roomAvailable = isRoomAvailable(bookingRequest.getStartTime(), bookingRequest.getEndTime(),
                    bookingQueueRequest.getRoomId());
            if(roomAvailable) {
                BookingDetails bookingDetails = bookingDetailsConverter.convert(bookingQueueRequest);
                log.info("Save booking details successfully. for roomNo : {} fromTime: {}, toTime :{} ",
                        bookingDetails.getRoomId(), bookingDetails.getStartTime(), bookingDetails.getEndTime());
                bookingRepository.save(bookingDetails);

                BookingResponse response = bookingDetailsConverter.convert(bookingDetails,
                        bookingQueueRequest.getRoomName());

                // Sending Success mail to customer with booking_id

                return response;
            }
            // Sending failure mail with no room avilability.

        } catch (Exception e) {
            log.error("exception occured while processing the booking...");
        }
        return null;
    }
}
