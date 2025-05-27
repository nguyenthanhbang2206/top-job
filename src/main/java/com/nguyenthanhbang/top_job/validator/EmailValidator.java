package com.nguyenthanhbang.top_job.validator;

import com.nguyenthanhbang.top_job.model.User;
import com.nguyenthanhbang.top_job.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private boolean checkExist;
    private UserRepository userRepository;
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.checkExist = constraintAnnotation.checkValid();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.hasLength(s)){
            return false;
        }
        String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if(!s.matches(regex)){
            return false;
        }else {
            if(checkExist) {
                User user = userRepository.findByEmail(s);
                if(user == null) {
                    constraintValidatorContext
                            .buildConstraintViolationWithTemplate("Email already exists")
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
