package com.nguyenthanhbang.top_job.dto.request;


import com.nguyenthanhbang.top_job.model.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JobCriteria {
    private String location;
    private Job.JobLevel level;
    private List<String> salary;
    private List<String> skill;
}
