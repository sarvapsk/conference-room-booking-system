package com.mashreq.confbooking.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingConfirmationResponse {

    private String status;

    private String message;

    private String roomName;
}
