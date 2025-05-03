package com.nguyenthanhbang.top_job.repository;

import com.nguyenthanhbang.top_job.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByRefreshTokenAndEmail(String refreshToken, String email);
}
