package com.mashreq.confbooking.exception.handler;

import lombok.Data;

@Data
public class BookingSystemException extends RuntimeException {

    private String errorCode;
    private String message;
    private Object errorData;

    public BookingSystemException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public BookingSystemException(String errorCode, String message, Object errorData) {
        super();
        this.errorCode = errorCode;
        this.message = message;
        this.errorData = errorData;
    }
}
