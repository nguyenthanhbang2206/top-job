package com.nguyenthanhbang.top_job.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(
        validatedBy = {RegisterValidator.class}
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRegister {
    String message() default "Register invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
