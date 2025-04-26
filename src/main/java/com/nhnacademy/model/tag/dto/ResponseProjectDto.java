package com.nhnacademy.model.tag.dto;

import com.nhnacademy.model.project.type.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProjectDto {

    private long projectId;
    private String projectName;
    private State projectState;

}
