package com.mashreq.confbooking.validator;

import com.mashreq.confbooking.util.DateUtils;
import com.mashreq.confbooking.validator.annotation.CurrentDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class CurrentDateValidator implements ConstraintValidator<CurrentDate, String> {

    public CurrentDateValidator() {
    }

    public void initialize(CurrentDate inputDate) {
    }

    public boolean isValid(String inputDate, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.isNotEmpty(inputDate) && compareDateWithCurrentDate(inputDate);
    }

    private boolean compareDateWithCurrentDate(String inputDtStr) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtils.BOOKING_DATE_FORMAT);
        try {
            LocalDate inputDt = LocalDate.parse(inputDtStr, formatter);
            return inputDt.equals(currentDate);
        } catch (DateTimeParseException e) {
            log.error("Received date is not current date {}", inputDtStr);
        }
        return false;
    }
}