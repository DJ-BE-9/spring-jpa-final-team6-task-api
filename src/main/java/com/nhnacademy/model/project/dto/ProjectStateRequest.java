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
public class ProjectStateRequest {

    private long projectId;
    private State projectState;

}
