package com.nhnacademy.model.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterProjectRequest {
    long projectId;

    @NotNull
    String projectName;

    @NotNull
    String projectState;

}
