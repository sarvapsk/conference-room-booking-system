package com.mashreq.confbooking.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MeetingRoomResponse {

    List<MeetingRoomDetails> availableMeetingRoomList;

}
