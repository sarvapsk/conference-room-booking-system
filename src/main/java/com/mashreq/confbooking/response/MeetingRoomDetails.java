package com.mashreq.confbooking.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeetingRoomDetails {
    private String meetingRoomName;
    private int maxCapcity;
}
