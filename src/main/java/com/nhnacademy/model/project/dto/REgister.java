package com.nhnacademy.model.project.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class REgister {
    private long projectId;

    @NotNull
    private String projectName;

    @NotNull
    private String projectState;


    //fk
    @NotNull
    private long proejctManagerId;
}
