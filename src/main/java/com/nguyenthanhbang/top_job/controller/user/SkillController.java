package com.nguyenthanhbang.top_job.controller.user;

import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.model.Skill;
import com.nguyenthanhbang.top_job.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skills)
                .message("Get skills success")
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{skillId}")
    public ResponseEntity<ApiResponse<Skill>> getSkillById(@PathVariable Long skillId){
        Skill skill = skillService.getSkillById(skillId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skill)
                .message("Get skill by id success")
                .build();
        return ResponseEntity.ok(response);
    }
}
