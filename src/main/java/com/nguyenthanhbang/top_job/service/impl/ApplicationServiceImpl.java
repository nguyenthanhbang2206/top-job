package com.nguyenthanhbang.top_job.service.impl;


import com.nguyenthanhbang.top_job.dto.request.ApplicationRequest;
import com.nguyenthanhbang.top_job.model.Application;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.model.User;
import com.nguyenthanhbang.top_job.repository.ApplicationRepository;
import com.nguyenthanhbang.top_job.service.ApplicationService;
import com.nguyenthanhbang.top_job.service.JobService;
import com.nguyenthanhbang.top_job.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final UserService userService;
    private final JobService jobService;
    @Override
    public Application createApplication(Long jobId,ApplicationRequest request) {
        User user = userService.getUserLogin();
        Job job = jobService.getJobById(jobId);
        Application application = new Application();
        application.setEmail(request.getEmail());
        application.setUrl(request.getUrl());
        application.setStatus(Application.ApplicationStatus.PENDING);
        application.setUser(user);
        application.setJob(job);
        application =  applicationRepository.save(application);
        return application;
    }

    @Override
    public Application updateApplication(Long applicationId, Application.ApplicationStatus status) {
        Application application = this.getApplicationById(applicationId);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        Application application = this.getApplicationById(applicationId);
        applicationRepository.deleteById(applicationId);
    }

    @Override
    public List<Application> getAllApplication() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(()->new EntityNotFoundException("Application not found"));
        return application;
    }

    @Override
    public List<Application> getAllApplicationByJobId(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
