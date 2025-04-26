package com.nhnacademy.model.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProjectRequest {
    private String projectName;
    private String projectState;
}
