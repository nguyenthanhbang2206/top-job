package com.nguyenthanhbang.top_job.controller.admin;

import com.nguyenthanhbang.top_job.dto.request.JobCriteria;
import com.nguyenthanhbang.top_job.dto.request.JobRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
@RestController
@RequiredArgsConstructor
public class AdminJobController {
    private final JobService jobService;
    @PostMapping("/jobs")
    public ResponseEntity<ApiResponse<Job>> createJob(@RequestBody JobRequest request){
        Job job = jobService.createJob(request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Job created")
                .status(HttpStatus.CREATED.value())
                .data(job)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<Job>> updateJob(@PathVariable Long jobId,@RequestBody JobRequest request){
        Job job = jobService.updateJob(jobId, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Job updated")
                .status(HttpStatus.OK.value())
                .data(job)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<Job>> getJobById(@PathVariable Long jobId){
        Job job = jobService.getJobById(jobId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get job success")
                .status(HttpStatus.OK.value())
                .data(job)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<PaginationResponse<Job>>> getAllJobs(JobCriteria jobCriteria, Pageable pageable){
        PaginationResponse<Job> paginationResponse = jobService.getAllJobs(jobCriteria, pageable);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get jobs success")
                .status(HttpStatus.OK.value())
                .data(paginationResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<ApiResponse<Void>> deleteJob(@PathVariable Long jobId){
        jobService.deleteJob(jobId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Delete job success")
                .status(HttpStatus.OK.value())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
