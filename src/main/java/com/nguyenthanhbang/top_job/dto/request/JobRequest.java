package com.nguyenthanhbang.top_job.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nguyenthanhbang.top_job.model.Application;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.model.Skill;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class JobRequest {

    private String name;
    private String location;
    private String description;
    private long salary;
    private int quantity;
    private Instant startDate;
    private Instant endDate;
    private Job.JobLevel level;
    private Long companyId;
    private List<Long> skillIds;
}
