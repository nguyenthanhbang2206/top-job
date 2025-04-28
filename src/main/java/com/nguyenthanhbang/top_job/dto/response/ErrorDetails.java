package com.nguyenthanhbang.top_job.dto.response;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
    private int status;
    private String error;
    private String path;
    private String message;
    private Instant timestamp;

}
