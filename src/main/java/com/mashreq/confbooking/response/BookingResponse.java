package com.mashreq.confbooking.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {

    private String status;
    private String bookingId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String roomName;
}
