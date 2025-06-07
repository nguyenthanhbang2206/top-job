package com.nguyenthanhbang.top_job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Skill extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs;


}