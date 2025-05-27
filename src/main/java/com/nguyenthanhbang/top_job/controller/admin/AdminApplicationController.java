package com.nguyenthanhbang.top_job.controller.admin;

import com.nguyenthanhbang.top_job.dto.request.JobRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.model.Application;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminApplicationController {
    private final ApplicationService applicationService;
    @PutMapping("/applications/{applicationId}")
    public ResponseEntity<ApiResponse<Application>> updateApplication(@PathVariable Long applicationId, @RequestParam Application.ApplicationStatus status){
        Application application = applicationService.updateApplication(applicationId, status);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Application updated")
                .status(HttpStatus.OK.value())
                .data(application)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/applications/{applicationId}")
    public ResponseEntity<ApiResponse<Void>> deleteApplication(@PathVariable Long applicationId){
        applicationService.deleteApplication(applicationId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Application deleted")
                .status(HttpStatus.OK.value())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/applications")
    public ResponseEntity<ApiResponse<List<Application>>> getAllApplications(){
        List<Application> applications = applicationService.getAllApplication();
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get all applications")
                .status(HttpStatus.OK.value())
                .data(applications)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/jobs/{jobId}/applications")
    public ResponseEntity<ApiResponse<List<Application>>> getApplicationsByJobId(@PathVariable Long jobId){
        List<Application> applications = applicationService.getAllApplicationByJobId(jobId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get all applications by jobId success")
                .status(HttpStatus.OK.value())
                .data(applications)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
