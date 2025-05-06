package com.nguyenthanhbang.top_job.repository;

import com.nguyenthanhbang.top_job.model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Long> {
    boolean existsByToken(String token);
}
