package com.nguyenthanhbang.top_job.service.impl;

import com.nguyenthanhbang.top_job.dto.request.CreateUserRequest;
import com.nguyenthanhbang.top_job.dto.request.UpdateUserRequest;
import com.nguyenthanhbang.top_job.model.User;
import com.nguyenthanhbang.top_job.repository.UserRepository;
import com.nguyenthanhbang.top_job.service.UserService;
import com.nguyenthanhbang.top_job.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final PasswordEncoder passwordEncoder;



    @Override
    public void updateTokenOfUser(String email, String refreshToken) {
        User user = this.getUserByEmail(email);
        user.setRefreshToken(refreshToken);
        user = userRepository.save(user);
    }

    @Override
    public User createUser(CreateUserRequest request) {
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFullName(request.getFullName());
        newUser.setRole(request.getRole());
        newUser = userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User getUserByRefreshTokenAndEmail(String refreshToken, String email) {
        User user = userRepository.findByRefreshTokenAndEmail(refreshToken, email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User getUserLogin() {
        String email = SecurityUtil.getCurrentUserLogin().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        User user = this.getUserByEmail(email);
        return user;
    }

    @Override
    public User updateProfile(UpdateUserRequest request) {
        User user = this.getUserLogin();
        user.setAddress(request.getAddress());
        user.setAvatar(request.getAvatar());
        user.setFullName(request.getFullName());
        user.setGender(request.getGender());
        user = userRepository.save(user);
        return user;
    }
}
