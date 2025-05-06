package com.nguyenthanhbang.top_job.service;

import com.nguyenthanhbang.top_job.dto.request.SkillRequest;
import com.nguyenthanhbang.top_job.model.Skill;

import java.util.List;

public interface SkillService {
    Skill createSkill(SkillRequest request);
    Skill updateSkill(Long skillId, SkillRequest request);
    void  deleteSkill(Long skillId);
    List<Skill> getAllSkills();
    Skill getSkillById(Long skillId);
}
