package com.nguyenthanhbang.top_job.service.impl;

import com.nguyenthanhbang.top_job.dto.request.SkillRequest;
import com.nguyenthanhbang.top_job.model.Skill;
import com.nguyenthanhbang.top_job.repository.SkillRepository;
import com.nguyenthanhbang.top_job.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Skill createSkill(SkillRequest request) {
        Skill skill = new Skill();
        skill.setName(request.getName());
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Long skillId, SkillRequest request) {
        Skill skill = this.getSkillById(skillId);
        skill.setName(request.getName());
        return skillRepository.save(skill);
    }

    @Override
    public void deleteSkill(Long skillId) {
        Skill skill = this.getSkillById(skillId);
        skillRepository.deleteById(skillId);
    }

    @Override
    public List<Skill> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills;
    }

    @Override
    public Skill getSkillById(Long skillId) {
        Skill skill = skillRepository.findById(skillId).orElseThrow(() -> new EntityNotFoundException("Skill not found"));
        return skill;
    }
}
