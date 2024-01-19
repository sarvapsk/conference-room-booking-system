package com.mashreq.confbooking.controller;

import com.mashreq.confbooking.request.room.RoomAdditionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface RoomOperations {

    @GetMapping(value = "/retrieve-available-rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrieveAvailableRooms(@Valid @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid start time in HH:mm format. Eg. 09:59") @RequestParam(required = true, name = "startTime") String startTime,
                                                    @Valid @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid end time in HH:mm format. Eg. 09:59")  @RequestParam(required = true, name = "endTime") String endTime) ;

    public ResponseEntity<?> addRoom(@Valid @RequestBody RoomAdditionRequest roomAdditionRequest) ;

}
