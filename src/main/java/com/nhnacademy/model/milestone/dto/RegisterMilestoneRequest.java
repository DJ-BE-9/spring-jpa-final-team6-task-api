package com.nhnacademy.model.milestone.dto;

import com.nhnacademy.model.project.entity.Project;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterMilestoneRequest {
    @NotNull
    String milestoneName;

    @NotNull
    Project project;
}
