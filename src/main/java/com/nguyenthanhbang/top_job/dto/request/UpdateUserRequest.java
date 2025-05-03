package com.nguyenthanhbang.top_job.dto.request;


import com.nguyenthanhbang.top_job.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank(message = "FullName must not be blank")
    private String fullName;
    @NotBlank(message = "Avatar must not be blank")
    private String avatar;
    private User.Gender gender;
    private String address;
}
