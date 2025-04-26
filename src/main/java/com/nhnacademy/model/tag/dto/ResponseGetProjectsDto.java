package com.nhnacademy.model.tag.dto;

import com.nhnacademy.model.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetProjectsDto {

    private List<Project> projects;

}
