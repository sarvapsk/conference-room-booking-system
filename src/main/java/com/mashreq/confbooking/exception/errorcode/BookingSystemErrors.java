package com.mashreq.confbooking.exception.errorcode;

import lombok.Getter;

@Getter
public enum BookingSystemErrors {

    E_AUTH("E_AUTH", "You are not authorised to login"),
    E_OVERLAPPING_WITH_MAINTANENCE_TIME("E_OVERLAPPING_WITH_MAINTANENCE_TIME", "Please select different time because rooms are not available due to mainatanence."),
    E_ROOMS_NOT_AVAILABLE("E_ROOMS_NOT_AVAILABLE", "Sorry, all rooms ares booked in the given time. Please choose different time."),
    E_INVALID_TIME("E_INVALID_TIME", "Please select correct time with 15 mins of interval.");

    String errorCode;
    String message;

    BookingSystemErrors(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
