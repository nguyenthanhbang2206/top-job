package com.nguyenthanhbang.top_job.model;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidToken extends BaseEntity{
    @Column(columnDefinition = "TEXT")
    private String token;
}
