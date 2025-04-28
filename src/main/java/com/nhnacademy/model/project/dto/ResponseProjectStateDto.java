package com.nhnacademy.model.project.dto;

import com.nhnacademy.model.project.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProjectStateDto {

    private long projectId;
    private String projectName;
    private State projectState;

}
