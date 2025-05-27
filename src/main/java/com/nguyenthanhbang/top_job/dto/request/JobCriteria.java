package com.nguyenthanhbang.top_job.dto.request;


import com.nguyenthanhbang.top_job.model.Job;
import lombok.Getter;

import java.util.List;
@Getter
public class JobCriteria {
    private String location;
    private Job.JobLevel level;
    private List<String> salary;
    private List<String> skill;
}
