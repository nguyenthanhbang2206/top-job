package com.nguyenthanhbang.top_job.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;


@Getter
public class CompanyRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Description must not be blank")
    private String description;
    @NotBlank(message = "Address must not be blank")
    private String address;
    @NotBlank(message = "Logo must not be blank")
    private String logo;
}
