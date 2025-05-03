package com.nguyenthanhbang.top_job.dto.request;

import com.nguyenthanhbang.top_job.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String email;
    private String password;
    private String confirmPassword;
    @NotBlank(message = "Fullname must not be blank")
    private String fullName;
    private User.Role role;
}
