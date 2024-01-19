package com.mashreq.confbooking.response;

import com.mashreq.confbooking.exception.errorcode.ErrorResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse<T> {

    private String message;

    private ErrorResponseStatus status;

    private String errorCode;

    private T errors;
}
