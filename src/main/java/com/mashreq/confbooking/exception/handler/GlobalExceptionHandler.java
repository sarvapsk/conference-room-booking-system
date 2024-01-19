package com.mashreq.confbooking.exception.handler;

import com.mashreq.confbooking.exception.errorcode.ErrorResponseStatus;
import com.mashreq.confbooking.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.web.util.HtmlUtils.htmlEscape;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookingSystemException.class)
    ResponseEntity onBookingSystemException(BookingSystemException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .message(String.valueOf(ex.getMessage()))
                .errorCode(ex.getErrorCode())
                .build();

        return sendException(ex, response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    ResponseEntity onConstraintValidationException(RoomNotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(ErrorResponseStatus.ERROR)
                .message(String.valueOf(ex.getMessage()))
                .errorCode(ex.getErrorCode())
                .build();

        return sendException(ex, response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .message("Invalid Request")
                .status(ErrorResponseStatus.ERROR)
                .errorCode("INVALID_REQUEST")
                .errors(ex.getBindingResult().getFieldErrors().stream()
                        .collect(Collectors.groupingBy(FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList()))))
                .build();

        return sendException(ex, response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity onConstraintValidationException(ConstraintViolationException ex) {

        ErrorResponse response = ErrorResponse.builder()
                .message("Invalid Request")
                .status(ErrorResponseStatus.ERROR)
                .errorCode("INVALID_REQUEST")
                .errors(ex.getConstraintViolations().stream()
                        .collect(Collectors.groupingBy(ConstraintViolation::getPropertyPath,
                                Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList()))))
                .build();

        return sendException(ex, response, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity sendException(Throwable ex, ErrorResponse response, HttpStatus httpStatus) {
        log.error("Exception Occured while booking the room: {}", htmlEscape(ex.getMessage()));
        return ResponseEntity.status(httpStatus).body(response);
    }




}
