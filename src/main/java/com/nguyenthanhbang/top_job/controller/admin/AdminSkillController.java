package com.nguyenthanhbang.top_job.controller.admin;

import com.nguyenthanhbang.top_job.dto.request.SkillRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.model.Skill;
import com.nguyenthanhbang.top_job.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/skills")
@RequiredArgsConstructor
public class AdminSkillController {
    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<ApiResponse<Skill>> createSkill(@RequestBody SkillRequest request){
        Skill skill = skillService.createSkill(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(skill)
                .message("Skill created")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{skillId}")
    public ResponseEntity<ApiResponse<Skill>> updateSkill(@PathVariable Long skillId,@RequestBody SkillRequest request){
        Skill skill = skillService.updateSkill(skillId, request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(skill)
                .message("Skill updated")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@PathVariable Long skillId){
        skillService.deleteSkill(skillId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(null)
                .message("Skill deleted")
                .build();
        return ResponseEntity.ok(response);
    }

}
