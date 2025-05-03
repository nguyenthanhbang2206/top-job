package com.nguyenthanhbang.top_job.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository {
    boolean existsByToken(String token);
}
