package com.nguyenthanhbang.top_job.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "invalidTokens")
public class InvalidToken extends BaseEntity{
    @Column(columnDefinition = "TEXT")
    private String token;
}
