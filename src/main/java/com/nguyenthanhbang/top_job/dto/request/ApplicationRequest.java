package com.nguyenthanhbang.top_job.dto.request;

import com.nguyenthanhbang.top_job.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ApplicationRequest {
    @NotBlank(message = "Url must not be blank")
    private String url;
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotNull(message = "User must not be null")
    private User user;
}
