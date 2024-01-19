package com.mashreq.confbooking.request.booking;

import com.mashreq.confbooking.validator.annotation.CurrentDate;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BookingRequest implements Serializable {

    @NotBlank(message = "Email id is required")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email must be valid. E.g: info@example.com")
    private String emailId;

    @NotNull(message = "Booking date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Please enter valid date in yyyy-MM-dd format.")
    @CurrentDate(message = "Please choose current date for booking. Future and Past dates are not allowed.")
    private String bookingDate;

    @NotBlank(message = "Start time is required")
    @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid time in HH:mm format. Eg. 09:59")
    private String startTime;

    @NotBlank(message = "End time is required")
    @Pattern(regexp = "^([01]?\\d|2[0-3]):([0-5]\\d)$", message = "Please enter valid time in HH:mm format. Eg. 23:59")
    private String endTime;

    @Min(value = 2, message = "Attendance count should be greater than 1.")
    private int attendeesCount;
}
