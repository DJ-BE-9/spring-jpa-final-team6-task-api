package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.project.type.State;
import com.nhnacademy.repository.ProjectMemberRepository;
import com.nhnacademy.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectServiceImpl(projectRepository, projectMemberRepository);
    }

    @Test
    void existsProject() {
        long projectId = 1L;
        when(projectRepository.existsById(projectId)).thenReturn(true);

        boolean result = projectService.existsProject(projectId);

        assertTrue(result);
    }

    @Test
    void registerProject() {
        RegisterProjectRequest request = new RegisterProjectRequest("프로젝트", "ACTIVE");
        Project savedProject = new Project(1L, "프로젝트", State.ACTIVE);

        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);

        long result = projectService.registerProject(request);

        assertEquals(1L, result);
    }

    @Test
    void registerProject_NullValues() {
        RegisterProjectRequest request = new RegisterProjectRequest(null, null);

        assertThrows(IllegalArgumentException.class, () -> projectService.registerProject(request));
    }

    @Test
    void deleteProject() {
        long projectId = 1L;
        when(projectRepository.existsById(projectId)).thenReturn(true);

        projectService.deleteProject(projectId);

        verify(projectRepository).deleteById(projectId);
    }

    @Test
    void deleteProject_NotFound() {
        long projectId = 1L;
        when(projectRepository.existsById(projectId)).thenReturn(false);

        assertThrows(ProjectNotFoundException.class, () -> projectService.deleteProject(projectId));
    }

    @Test
    void updateProject() {
        long projectId = 1L;
        UpdateProjectRequest request = new UpdateProjectRequest("업데이트 프로젝트", "COMPLETED");

        projectService.updateProject(projectId, request);

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void updateProject_NullValues() {
        long projectId = 1L;
        UpdateProjectRequest request = new UpdateProjectRequest(null, null);

        assertThrows(IllegalArgumentException.class, () -> projectService.updateProject(projectId, request));
    }

    @Test
    void getAllProjectsByUserId() {
        String memberId = "user1";
        List<Project> projects = List.of(new Project("프로젝트1", State.ACTIVE), new Project("프로젝트2", State.COMPLETED));

        when(projectMemberRepository.findAllProjectByUserId(memberId)).thenReturn(projects);

        List<Project> result = projectService.getAllProjectsByUserId(memberId);

        assertEquals(projects, result);
    }

    @Test
    void getProjectById() {
        long projectId = 1L;
        Project project = new Project("프로젝트", State.ACTIVE);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(projectId);

        assertEquals(project, result);
    }

    @Test
    void getProjectById_NotFound() {
        long projectId = 1L;
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectById(projectId));
    }
}