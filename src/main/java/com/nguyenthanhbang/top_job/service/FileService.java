package com.nguyenthanhbang.top_job.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(MultipartFile file, String folder) throws IOException;
}
