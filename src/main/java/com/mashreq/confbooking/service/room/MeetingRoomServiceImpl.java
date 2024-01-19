package com.mashreq.confbooking.service.room;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.enums.RoomType;
import com.mashreq.confbooking.exception.errorcode.BookingSystemErrors;
import com.mashreq.confbooking.exception.handler.BookingSystemException;
import com.mashreq.confbooking.model.BookingDetails;
import com.mashreq.confbooking.model.RoomDetails;
import com.mashreq.confbooking.repository.BookingRepository;
import com.mashreq.confbooking.repository.RoomRepository;
import com.mashreq.confbooking.request.room.RoomAdditionRequest;
import com.mashreq.confbooking.response.MeetingRoomDetails;
import com.mashreq.confbooking.response.MeetingRoomResponse;
import com.mashreq.confbooking.service.AbstractRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingRoomServiceImpl extends AbstractRoomService implements MeetingRoomService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomRepository roomRepository;

    @Value("${booking.system.allowed.interval}")
    String systemAllowedInterval;

    @Override
    public MeetingRoomResponse getAvailableMeetingRooms(String startTime, String endTime) {

        if(!hasSpecifiedInterval(startTime, endTime, Integer.valueOf(systemAllowedInterval))) {
            throw  new BookingSystemException(BookingSystemErrors.E_INVALID_TIME.getErrorCode(),
                    BookingSystemErrors.E_INVALID_TIME.getMessage());
        }

        List<RoomDetails> availableRooms = roomRepository
                .findByIsActiveOrderByMaxCapacityAsc(true)
                .stream().filter(eachRoom -> !hasOverlappedWithMaintanence(startTime, endTime, eachRoom)
                        && isRoomAvailable(startTime, endTime, eachRoom.getId())).toList();

        List<MeetingRoomDetails> roomDetailsList = new ArrayList<>();

        availableRooms.forEach(eachRoom -> {
            roomDetailsList.add(MeetingRoomDetails.builder()
                    .meetingRoomName(eachRoom.getName())
                    .maxCapcity(eachRoom.getMaxCapacity())
                    .build());
        });

        return MeetingRoomResponse.builder().availableMeetingRoomList(roomDetailsList).build();
    }

    @Override
    public MeetingRoomDetails addMeetingRoom(RoomAdditionRequest roomAdditionRequest) {
        RoomDetails roomDetails = new RoomDetails();
        roomDetails.setRoomType(RoomType.valueOf(roomAdditionRequest.getRoomType()));
        roomDetails.setActive(true);
        roomDetails.setName(roomAdditionRequest.getName());
        roomDetails.setAvailableFrom(roomAdditionRequest.getAvailableFrom());
        roomDetails.setAvailableTo(roomAdditionRequest.getAvailableTo());
        roomDetails.setMaxCapacity(roomAdditionRequest.getMaxCapacity());

        roomRepository.save(roomDetails);

        return MeetingRoomDetails.builder()
                .meetingRoomName(roomAdditionRequest.getName())
                .maxCapcity(roomAdditionRequest.getMaxCapacity()).build();

    }

}
