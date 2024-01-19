package com.mashreq.confbooking.request.room;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RoomAdditionRequest {

    @NotNull(message = "Room Name is required.")
    private String name;

    @NotBlank(message = "Available from time is required")
    @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid available from time in HH:mm format. Eg. 09:45")
    private String availableFrom;

    @NotBlank(message = "Available To time is required")
    @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid available to time in HH:mm format. Eg. 09:59")
    private String availableTo;

    @NotBlank(message = "Room type should not be blank.")
    @Pattern(regexp = "CONFERENCE_ROOM|AUDITORIUM", message = "Please provide valid room type.")
    private String roomType;

    @Min(value = 3, message = "The Meeting room capacity should be atleast 3.")
    private int maxCapacity;

    private String[] maintanenceTime;
}
