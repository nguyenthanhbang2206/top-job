package com.nguyenthanhbang.top_job.controller.user;

import com.nguyenthanhbang.top_job.dto.request.JobCriteria;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    @GetMapping("/{jobId}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long jobId){
        Job job = jobService.getJobById(jobId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get job success")
                .status(HttpStatus.OK.value())
                .data(job)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<Job>>> getAllJobs(JobCriteria jobCriteria, Pageable pageable){
        PaginationResponse<Job> paginationResponse = jobService.getAllJobs(jobCriteria, pageable);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get jobs success")
                .status(HttpStatus.OK.value())
                .data(paginationResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
