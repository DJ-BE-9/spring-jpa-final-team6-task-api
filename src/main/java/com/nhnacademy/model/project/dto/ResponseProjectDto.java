package com.nhnacademy.model.project.dto;

import com.nhnacademy.model.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseProjectDto {
    private List<Project> projects;
}
