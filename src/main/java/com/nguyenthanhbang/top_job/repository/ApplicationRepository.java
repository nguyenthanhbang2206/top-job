package com.nguyenthanhbang.top_job.repository;

import com.nguyenthanhbang.top_job.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobId(Long jobId);
}
