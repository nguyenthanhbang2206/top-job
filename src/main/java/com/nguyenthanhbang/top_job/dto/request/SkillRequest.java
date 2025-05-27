package com.nguyenthanhbang.top_job.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SkillRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
}
