package com.nhnacademy.service;

import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;

import java.util.List;

public interface ProjectService {
    boolean existsProject(long projectId);
    Project registerProject(RegisterProjectRequest registerProjectRequest);
    void deleteProject(Long projectId);
    void updateProject(Long projectId, UpdateProjectRequest updateProjectRequest);
    List<Project> getAllProjectsByUserId(String userId);

    Project getProjectById(Long projectId);

}
