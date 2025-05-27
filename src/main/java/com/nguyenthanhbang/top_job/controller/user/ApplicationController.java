package com.nguyenthanhbang.top_job.controller.user;

import com.nguyenthanhbang.top_job.dto.request.ApplicationRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.model.Application;
import com.nguyenthanhbang.top_job.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    @PostMapping("/jobs/{jobId}/applications")
    public ResponseEntity<ApiResponse<Application>> createApplication(@PathVariable Long jobId, @RequestBody ApplicationRequest request) {
        Application application = applicationService.createApplication(jobId, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Application created")
                .status(HttpStatus.CREATED.value())
                .data(application)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping("/applications/{applicationId}")
    public ResponseEntity<ApiResponse<Application>> getApplicationById(@PathVariable Long applicationId) {
        Application application = applicationService.getApplicationById(applicationId);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Get application by id success")
                .status(HttpStatus.OK.value())
                .data(application)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
