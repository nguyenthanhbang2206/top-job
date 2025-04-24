package com.nguyenthanhbang.top_job.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application extends BaseEntity{
    private String url;
    private String email;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;
    public enum ApplicationStatus {
        PENDING,REVIEWING,APPROVED,REJECTED
    }
    
}