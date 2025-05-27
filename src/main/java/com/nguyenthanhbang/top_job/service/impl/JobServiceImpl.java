package com.nguyenthanhbang.top_job.service.impl;

import com.nguyenthanhbang.top_job.dto.request.JobCriteria;
import com.nguyenthanhbang.top_job.dto.request.JobRequest;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.model.Job;
import com.nguyenthanhbang.top_job.model.Skill;
import com.nguyenthanhbang.top_job.repository.JobRepository;
import com.nguyenthanhbang.top_job.repository.SkillRepository;
import com.nguyenthanhbang.top_job.service.CompanyService;
import com.nguyenthanhbang.top_job.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final SkillRepository skillRepository;
    private final CompanyService companyService;

    @Override
    public Job createJob(JobRequest request) {
        List<Skill> skill = skillRepository.findByIdIn(request.getSkillIds());
        Company company = companyService.getCompanyById(request.getCompanyId());
        Job job = new Job();
        job.setName(request.getName());
        job.setDescription(request.getDescription());
        job.setActive(true);
        job.setLevel(request.getLevel());
        job.setLocation(request.getLocation());
        job.setQuantity(request.getQuantity());
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setSalary(request.getSalary());
        job.setSkills(skill);
        job.setCompany(company);
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Long jobId, JobRequest request) {
        Job job = this.getJobById(jobId);
        List<Skill> skill = skillRepository.findByIdIn(request.getSkillIds());
        Company company = companyService.getCompanyById(request.getCompanyId());
        job.setName(request.getName());
        job.setDescription(request.getDescription());
        job.setLevel(request.getLevel());
        job.setLocation(request.getLocation());
        job.setQuantity(request.getQuantity());
        job.setStartDate(request.getStartDate());
        job.setEndDate(request.getEndDate());
        job.setSalary(request.getSalary());
        job.setSkills(skill);
        job.setCompany(company);
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long jobId) {
        Job job = this.getJobById(jobId);
        job.setActive(false);
    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new EntityNotFoundException("Job not found"));
        return job;
    }
    private Specification<Job> hasLevel(Job.JobLevel level) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("level"), level);
        };
    }
    private Specification<Job> hasSkillIn(List<Skill> skills) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.in(root.get("skills")).value(skills);
        };
    }
    private Specification<Job> salaryRange(Long minSalary, Long maxSalary) {
        return (root, query, builder) -> {
            return builder.between(root.get("salary"), minSalary, maxSalary);
        };
    }
    private Specification<Job> minSalary(Long minSalary) {
        return (root, query, builder) -> {
            return builder.ge(root.get("salary"), minSalary);
        };
    }
    private Specification<Job> maxSalary(Long maxSalary) {
        return (root, query, builder) -> {
            return builder.le(root.get("salary"), maxSalary);
        };
    }
    private Specification<Job> matchSalary(List<String> salary) {
        Specification<Job> combinedSpec = Specification.where(null);
        if(salary != null){
            for(String item : salary) {
                if(item.equals("from-100-to-200")){
                    Specification<Job> spec = salaryRange(100L, 200L);
                    combinedSpec = combinedSpec.or(spec);
                }else if(item.equals("from-300-to-400")){
                    Specification<Job> spec = salaryRange(300L, 400L);
                    combinedSpec = combinedSpec.or(spec);
                }else if(item.equals("gt-400")){
                    Specification<Job> spec = minSalary(400L);
                    combinedSpec = combinedSpec.or(spec);
                }else if(item.equals("lt-100")){
                    Specification<Job> spec = maxSalary(100L);
                    combinedSpec = combinedSpec.or(spec);
                }
            }
        }
        return combinedSpec;
    }
    private Specification<Job> hasLocation(String location) {
        return (root, query, builder) -> {
            return builder.equal(root.get("location"), location);
        };
    }
    private Specification<Job> makeSpecs(JobCriteria jobCriteria){
        Specification<Job> spec = Specification.where(null);
        if(jobCriteria.getLevel() != null){
            spec = spec.and(hasLevel(jobCriteria.getLevel()));
        }
        if(jobCriteria.getSkill() != null){
            List<Skill> skillList = skillRepository.findByNameIn(jobCriteria.getSkill());
            spec = spec.and(hasSkillIn(skillList));
        }
        if(jobCriteria.getSalary() != null){
            spec = spec.and(matchSalary(jobCriteria.getSalary()));
        }
        if(jobCriteria.getLocation() != null){
            spec = spec.and(hasLocation(jobCriteria.getLocation()));
        }
        return spec;
    }

    @Override
    public PaginationResponse<Job> getAllJobs(JobCriteria jobCriteria, Pageable pageable) {
        Page<Job> jobPage = jobRepository.findAll(makeSpecs(jobCriteria), pageable);
        PaginationResponse.Pagination pagination = PaginationResponse.Pagination.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .totalPages(jobPage.getTotalPages())
                .totalItems(jobPage.getTotalElements())
                .build();
        PaginationResponse<Job> response = PaginationResponse.<Job>builder()
                .pagination(pagination)
                .items(jobPage.getContent())
                .build();
        return response;
    }
}
