package com.nguyenthanhbang.top_job.service;

import com.nguyenthanhbang.top_job.dto.request.JobCriteria;
import com.nguyenthanhbang.top_job.dto.request.JobRequest;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Job;
import org.springframework.data.domain.Pageable;

public interface JobService {
    Job createJob(JobRequest request);
    Job updateJob(Long jobId,JobRequest request);
    void deleteJob(Long jobId);
    Job getJobById(Long jobId);
    PaginationResponse<Job> getAllJobs(JobCriteria jobCriteria, Pageable pageable);
}
