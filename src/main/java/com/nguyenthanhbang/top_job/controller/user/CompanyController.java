package com.nguyenthanhbang.top_job.controller.user;

import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.dto.response.PaginationResponse;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable Long companyId){
        Company company = companyService.getCompanyById(companyId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(company)
                .message("Get company by id success")
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<PaginationResponse<Company>>> getAllCompany(Pageable pageable) {
        PaginationResponse<Company> paginationResponse = companyService.getAllCompanies(pageable);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(paginationResponse)
                .message("Get companies success")
                .build();
        return ResponseEntity.ok(response);
    }
}
