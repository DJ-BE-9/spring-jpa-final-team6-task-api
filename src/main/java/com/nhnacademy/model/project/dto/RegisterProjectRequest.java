package com.nhnacademy.model.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterProjectRequest {

    @NonNull
    private String projectName;
    @NonNull
    private String projectState;

}
