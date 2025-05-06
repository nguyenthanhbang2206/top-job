package com.nguyenthanhbang.top_job.service.impl;

import com.nguyenthanhbang.top_job.dto.request.CompanyRequest;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.repository.CompanyRepository;
import com.nguyenthanhbang.top_job.service.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public Company getCompanyById(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(()-> new EntityNotFoundException("Company not found"));
        return company;
    }

    @Override
    public Company createCompany(CompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setLogo(request.getLogo());
        company.setDescription(request.getDescription());
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long companyId, CompanyRequest request) {
        Company company = this.getCompanyById(companyId);
        company.setName(request.getName());
        company.setAddress(request.getAddress());
        company.setLogo(request.getLogo());
        company.setDescription(request.getDescription());
        return companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = this.getCompanyById(companyId);
        companyRepository.delete(company);
    }

    @Override
    public PaginationResponse<Company> getAllCompanies(Pageable pageable) {
        Page<Company> companyPage = companyRepository.findAll(pageable);
        PaginationResponse.Pagination pagination = PaginationResponse.Pagination.builder()
                .page(pageable.getPageNumber() + 1)
                .size(pageable.getPageSize())
                .totalItems(companyPage.getTotalElements())
                .totalPages(companyPage.getTotalPages())
                .build();
        PaginationResponse<Company> response = PaginationResponse.<Company>builder()
                .items(companyPage.getContent())
                .pagination(pagination)
                .build();
        return response;
    }
}
