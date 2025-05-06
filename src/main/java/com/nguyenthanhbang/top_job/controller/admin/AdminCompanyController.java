package com.nguyenthanhbang.top_job.controller.admin;

import com.nguyenthanhbang.top_job.dto.request.CompanyRequest;
import com.nguyenthanhbang.top_job.dto.response.ApiResponse;
import com.nguyenthanhbang.top_job.model.Company;
import com.nguyenthanhbang.top_job.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/companies")
@RequiredArgsConstructor
public class AdminCompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse<Company>> createCompany(@RequestBody CompanyRequest request){
        Company company = companyService.createCompany(request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .message("Company created")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{companyId}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(@PathVariable Long companyId,@RequestBody CompanyRequest request){
        Company company = companyService.updateCompany(companyId, request);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Company updated")
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable Long companyId){
        Company company = companyService.getCompanyById(companyId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get company by id success")
                .build();
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{companyId}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable Long companyId){
        companyService.deleteCompany(companyId);
        ApiResponse response = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Company deleted")
                .build();
        return ResponseEntity.ok(response);
    }


}
