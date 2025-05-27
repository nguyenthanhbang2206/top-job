package com.nguyenthanhbang.top_job.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenthanhbang.top_job.model.Application;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.model.Skill;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class JobRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Location must not be blank")
    private String location;
    @NotBlank(message = "Description must not be blank")
    private String description;
    @Min(value = 1, message = "Salary must be at least 1")
    private long salary;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    private Instant startDate;
    private Instant endDate;
    @NotNull(message = "Level must not be null")
    private Job.JobLevel level;
    @NotNull(message = "CompanyId must not be null")
    private Long companyId;
    @NotEmpty(message = "SkillIds must not be empty")
    private List<Long> skillIds;
}
