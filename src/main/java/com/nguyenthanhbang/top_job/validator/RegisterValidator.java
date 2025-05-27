package com.nguyenthanhbang.top_job.validator;

import com.nguyenthanhbang.top_job.dto.request.CreateUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<ValidRegister, CreateUserRequest> {

    @Override
    public void initialize(ValidRegister constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateUserRequest request, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        if(!request.getConfirmPassword().equals(request.getPassword())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Confirm password does not match")
                    .addPropertyNode("confirmPassword").addConstraintViolation();
            isValid = false;
        }
        return isValid;
    }
}
