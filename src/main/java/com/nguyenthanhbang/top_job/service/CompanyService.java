package com.nguyenthanhbang.top_job.service;

import com.nguyenthanhbang.top_job.dto.request.CompanyRequest;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Company;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    Company getCompanyById(Long companyId);
    Company createCompany(CompanyRequest request);
    Company updateCompany(Long companyId,CompanyRequest request);
    void deleteCompany(Long companyId);
    PaginationResponse<Company> getAllCompanies(Pageable pageable);
}
