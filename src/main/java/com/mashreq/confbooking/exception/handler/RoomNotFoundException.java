package com.mashreq.confbooking.exception.handler;

import lombok.Data;

@Data
public class RoomNotFoundException extends RuntimeException {

    private String errorCode;
    private String message;
    private Object errorData;

    public RoomNotFoundException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public RoomNotFoundException(String errorCode, String message, Object errorData) {
        super();
        this.errorCode = errorCode;
        this.message = message;
        this.errorData = errorData;
    }
}
