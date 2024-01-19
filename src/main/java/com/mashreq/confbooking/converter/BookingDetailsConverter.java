package com.mashreq.confbooking.converter;

import com.mashreq.confbooking.enums.BookingStatus;
import com.mashreq.confbooking.model.BookingDetails;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.request.queue.BookingQueueRequest;
import com.mashreq.confbooking.response.BookingResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mashreq.confbooking.util.DateUtils.getDate;
import static com.mashreq.confbooking.util.DateUtils.getStringDate;

@Component
public class BookingDetailsConverter {

    public BookingDetails convert(BookingQueueRequest bookingQueueRequest) {
        BookingDetails bookingDetails = new BookingDetails();
        BookingRequest bookingRequest = bookingQueueRequest.getBookingRequest();
        BeanUtils.copyProperties(bookingRequest, bookingDetails);
        bookingDetails.setBookingDate(getDate(bookingRequest.getBookingDate()));
        bookingDetails.setBookingId(UUID.randomUUID().toString());
        bookingDetails.setStatus(BookingStatus.BOOKED);
        bookingDetails.setRoomId(bookingQueueRequest.getRoomId());

        return bookingDetails;
    }

    public BookingResponse convert(BookingDetails bookingDetails, String roomName) {
        return BookingResponse.builder()
                .bookingId(bookingDetails.getBookingId())
                .roomName(roomName)
                .status(BookingStatus.BOOKED.name())
                .startTime(bookingDetails.getStartTime())
                .endTime(bookingDetails.getEndTime())
                .bookingDate(getStringDate(bookingDetails.getBookingDate()))
                .build();

    }



}
