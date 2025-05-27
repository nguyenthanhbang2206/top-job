package com.nguyenthanhbang.top_job.dto.request;

import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.model.User;

import lombok.Getter;

@Getter
public class ApplicationRequest {
    private String url;
    private String email;
    private User user;
}
