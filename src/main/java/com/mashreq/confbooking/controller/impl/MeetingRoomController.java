package com.mashreq.confbooking.controller.impl;

import com.mashreq.confbooking.controller.RoomOperations;
import com.mashreq.confbooking.request.room.RoomAdditionRequest;
import com.mashreq.confbooking.service.room.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class MeetingRoomController implements RoomOperations {

    @Autowired
    MeetingRoomService meetingRoomService;

    @Override
    public ResponseEntity<?> retrieveAvailableRooms(String startTime, String endTime) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingRoomService.getAvailableMeetingRooms(startTime, endTime));
    }

    @Override
    public ResponseEntity<?> addRoom(RoomAdditionRequest roomAdditionRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingRoomService.addMeetingRoom(roomAdditionRequest));
    }





}
