package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.EmptyRequestException;
import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.projectMember.AlreadyExistsMemberException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;
import com.nhnacademy.model.projectMember.entity.ProjectMember;
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

class ProjectMemberServiceImplTest {

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @Mock
    private ProjectRepository projectRepository;

    private ProjectMemberServiceImpl projectMemberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectMemberService = new ProjectMemberServiceImpl(projectMemberRepository, projectRepository);
    }

    @Test
    void registerMemberByProject() {
        long projectId = 1L;
        RegisterProjectMemberRequest request = new RegisterProjectMemberRequest("user1", true);
        Project project = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Project result = projectMemberService.registerMemberByProject(projectId, request);

        assertEquals(project, result);
        verify(projectMemberRepository).save(any(ProjectMember.class));
    }

    @Test
    void registerMemberByProject_ProjectNotFound() {
        long projectId = 1L;
        RegisterProjectMemberRequest request = new RegisterProjectMemberRequest("user1", true);

        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectMemberService.registerMemberByProject(projectId, request));
    }

    @Test
    void registerMemberByProject_NullRequest() {
        long projectId = 1L;

        assertThrows(IllegalArgumentException.class, () -> projectMemberService.registerMemberByProject(projectId, null));
    }

    @Test
    void deleteProject() {
        long projectId = 1L;

        projectMemberService.deleteProject(projectId);

        verify(projectMemberRepository).deleteByProject_ProjectId(projectId);
    }

    @Test
    void getProjectMembers() {
        long projectId = 1L;
        ProjectMember member1 = new ProjectMember(new Project(), "user1", true);
        ProjectMember member2 = new ProjectMember(new Project(), "user2", false);

        when(projectMemberRepository.findAllByProject_ProjectId(projectId)).thenReturn(List.of(member1, member2));

        var result = projectMemberService.getProjectMembers(projectId);

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getMemberId());
        assertTrue(result.get(0).isAdmin());
        assertEquals("user2", result.get(1).getMemberId());
        assertFalse(result.get(1).isAdmin());
    }

    @Test
    void getProjectMembers_InvalidProjectId() {
        long projectId = -1L;

        assertThrows(EmptyRequestException.class, () -> projectMemberService.getProjectMembers(projectId));
    }

    @Test
    void deleteProjectMemberByUserId() {
        long projectId = 1L;
        String memberId = "user1";

        projectMemberService.deleteProjectMemberByUserId(memberId, projectId);

        verify(projectMemberRepository).deleteProjectMemberByUserIdAndProject_ProjectId(memberId, projectId);
    }

    @Test
    void deleteProjectMemberByUserId_NullMemberId() {
        long projectId = 1L;

        assertThrows(EmptyRequestException.class, () -> projectMemberService.deleteProjectMemberByUserId(null, projectId));
    }

    @Test
    void registerMemberProject() {
        long projectId = 1L;
        RegisterProjectMemberRequest request = new RegisterProjectMemberRequest("user1", true);
        Project project = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findAllByProject_ProjectId(projectId)).thenReturn(List.of());

        projectMemberService.registerMemberProject(projectId, request);

        verify(projectMemberRepository).save(any(ProjectMember.class));
    }

    @Test
    void registerMemberProject_AlreadyExists() {
        long projectId = 1L;
        RegisterProjectMemberRequest request = new RegisterProjectMemberRequest("user1", true);
        Project project = new Project();
        ProjectMember existingMember = new ProjectMember(project, "user1", false);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findAllByProject_ProjectId(projectId)).thenReturn(List.of(existingMember));

        assertThrows(AlreadyExistsMemberException.class, () -> projectMemberService.registerMemberProject(projectId, request));
    }

    @Test
    void registerMemberProject_NullRequest() {
        long projectId = 1L;

        assertThrows(IllegalArgumentException.class, () -> projectMemberService.registerMemberProject(projectId, null));
    }
}
