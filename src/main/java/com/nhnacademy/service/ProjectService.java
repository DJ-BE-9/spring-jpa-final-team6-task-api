package com.nhnacademy.service;

import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;

import java.util.List;

public interface ProjectService {
    boolean existsProject(long projectId);
    void registerProject(RegisterProjectRequest registerProjectRequest);
    void deleteProject(Long projectId);
    void updateProject(Long projectId, UpdateProjectRequest updateProjectRequest);
    List<Project> getAllProjectsByUserId(String userId);
<<<<<<< HEAD
    Project getProjectById(Long projectId);
=======

    Project getProject(Long projectId);
>>>>>>> 8f2ecebe183c4b7001af35b9b8c37618eadf2f67

}
