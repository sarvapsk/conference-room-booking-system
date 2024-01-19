package com.mashreq.confbooking.service.room;

import com.mashreq.confbooking.request.room.RoomAdditionRequest;
import com.mashreq.confbooking.response.MeetingRoomDetails;
import com.mashreq.confbooking.response.MeetingRoomResponse;

public interface MeetingRoomService {
    public MeetingRoomResponse getAvailableMeetingRooms(String startTime, String endTime);

    public MeetingRoomDetails addMeetingRoom(RoomAdditionRequest roomAdditionRequest);

}
