package com.nguyenthanhbang.top_job.dto.request;

import com.nguyenthanhbang.top_job.model.User;
import com.nguyenthanhbang.top_job.validator.StrongPassword;
import com.nguyenthanhbang.top_job.validator.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    @ValidEmail(checkValid = true)
    private String email;
    @StrongPassword
    private String password;
    private String confirmPassword;
    @NotBlank(message = "Fullname must not be blank")
    private String fullName;
    private User.Role role;
}
