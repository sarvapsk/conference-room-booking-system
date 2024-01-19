package com.mashreq.confbooking.controller;

import com.mashreq.confbooking.request.booking.BookingRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface BookingOperations {

    @PostMapping(value = "/book-conf-room", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> bookConferenceRoom(@Valid @RequestBody BookingRequest roomBookingRequestDto);
}
