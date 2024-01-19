package com.mashreq.confbooking.service.booking;

import com.mashreq.confbooking.model.BookingDetails;
import com.mashreq.confbooking.request.booking.BookingRequest;
import com.mashreq.confbooking.request.queue.BookingQueueRequest;
import com.mashreq.confbooking.response.BookingConfirmationResponse;
import com.mashreq.confbooking.response.BookingResponse;

public interface BookingService {
    public BookingConfirmationResponse bookConferenceRoom(BookingRequest roomBookingRequest);

    public BookingResponse processBookingRequest(BookingQueueRequest bookingQueueRequest);

}
