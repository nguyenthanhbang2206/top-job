package com.nguyenthanhbang.top_job.repository;

import com.nguyenthanhbang.top_job.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByIdIn(List<Long> ids);
    List<Skill> findByNameIn(List<String> skills);
}
