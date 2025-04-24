package com.nhnacademy.model.projectMember.dto;

import com.nhnacademy.model.project.entity.Project;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterProjectMemberRequest {

    @NotNull
    Project project;
}
