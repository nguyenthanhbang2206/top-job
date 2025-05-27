package com.nguyenthanhbang.top_job.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {StrongPasswordValidator.class}
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {
    String message() default "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one digit, one special character, and must not contain any whitespace";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}