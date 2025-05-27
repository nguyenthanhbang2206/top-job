package com.nguyenthanhbang.top_job.service.impl;

import com.nguyenthanhbang.top_job.service.FileService;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final ServletContext servletContext;
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";
    @Override
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        if( file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be null or empty");
        }
        List<String> allowedExtensions = Arrays.asList("pdf","jpg","jpeg","png","doc","docx");
        boolean valid = allowedExtensions.stream().anyMatch(extension -> file.getOriginalFilename().toLowerCase().endsWith(extension));
        if(!valid) {
            throw new IllegalArgumentException("File is not a valid file");
        }
        Path path = Paths.get(UPLOAD_DIR, folder);
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileName = UUID.randomUUID() + this.getExtension(file.getOriginalFilename());
        Path uploadPath = path.resolve(fileName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadPath, StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }
    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
