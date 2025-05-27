package com.nguyenthanhbang.top_job.service;

import com.nguyenthanhbang.top_job.dto.request.ApplicationRequest;
import com.nguyenthanhbang.top_job.model.Application;

import java.util.List;

public interface ApplicationService {
    Application createApplication(Long jobId,ApplicationRequest request);
    Application updateApplication(Long applicationId, Application.ApplicationStatus status);
    void deleteApplication(Long applicationId);
    List<Application> getAllApplication();
    Application getApplicationById(Long applicationId);
    List<Application> getAllApplicationByJobId(Long jobId);
}
