package com.mashreq.confbooking.request.queue;

import com.mashreq.confbooking.request.booking.BookingRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingQueueRequest {
    private BookingRequest bookingRequest;
    private long roomId;
    private String roomName;
}
