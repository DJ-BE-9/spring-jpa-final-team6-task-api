package com.nhnacademy.service.impl;

import com.nhnacademy.exception.ProjectNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;
import com.nhnacademy.model.projectMember.entity.ProjectMember;
import com.nhnacademy.repository.ProjectMemberRepository;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectMemberServiceImpl implements ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Override
    public Project registerMemberByProject(RegisterProjectMemberRequest request, String userId) {
        if(Objects.isNull(request) || Objects.isNull(request.getProjectId()) || Objects.isNull(request.isProjectManager()) || userId.isEmpty()) {
            throw new IllegalArgumentException();
        }
        log.info("Registering project member with id {}", request.getProjectId());
        Project project= projectRepository.findById(request.getProjectId()).orElseThrow( () -> new ProjectNotFoundException("project not found"));
        ProjectMember projectMember = new ProjectMember(project, userId, request.isProjectManager());
        projectMemberRepository.save(projectMember);
        return project;
    }

    @Override
    public void deleteProject(long projectId) {
        projectMemberRepository.deleteByProject_ProjectId(projectId);
    }
}
