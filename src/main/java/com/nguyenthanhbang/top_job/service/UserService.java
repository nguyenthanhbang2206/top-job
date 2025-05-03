package com.nguyenthanhbang.top_job.service;

import com.nguyenthanhbang.top_job.dto.request.CreateUserRequest;
import com.nguyenthanhbang.top_job.dto.request.UpdateUserRequest;
import com.nguyenthanhbang.top_job.model.User;

public interface UserService {
    void updateTokenOfUser(String email, String refreshToken);
    User createUser(CreateUserRequest request);
    User getUserByRefreshTokenAndEmail(String refreshToken, String email);
    User getUserByEmail(String email);
    User getUserLogin();
    User updateProfile(UpdateUserRequest request);
}
