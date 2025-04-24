package com.nhnacademy.model.project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterProjectManagerRequest {

    @NotNull
    long proejctManagerId;
}
