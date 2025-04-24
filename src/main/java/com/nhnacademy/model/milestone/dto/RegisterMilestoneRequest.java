package com.nhnacademy.model.milestone.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterMilestoneRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long milestoneId;

    @NotNull
    private String milestoneName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="project_id")
    private Project project;
}
