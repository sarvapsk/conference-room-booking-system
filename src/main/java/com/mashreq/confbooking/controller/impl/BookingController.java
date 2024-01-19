package com.mashreq.confbooking.controller.impl;

import com.mashreq.confbooking.controller.BookingOperations;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.service.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class BookingController implements BookingOperations {

    @Autowired
    BookingService bookingService;

    @Override
    public ResponseEntity<?> bookConferenceRoom(BookingRequest roomBookingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.bookConferenceRoom(roomBookingRequestDto));
    }
}
