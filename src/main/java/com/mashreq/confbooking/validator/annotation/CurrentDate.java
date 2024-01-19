package com.mashreq.confbooking.validator.annotation;

import com.mashreq.confbooking.validator.CurrentDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentDateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentDate {
    String message() default "Invalid date.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}