package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
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
    public Project registerMemberByProject(long projectId, RegisterProjectMemberRequest request) {
        if(Objects.isNull(request) || Objects.isNull(request.isProjectManager()) || Objects.isNull(request.getMemberId())) {
            throw new IllegalArgumentException();
        }
        Project project = projectRepository.findById(projectId).orElseThrow( () -> new ProjectNotFoundException(projectId));

        ProjectMember projectMember = new ProjectMember(project, request.getMemberId(), request.isProjectManager());
        projectMemberRepository.save(projectMember);
        return project;
    }

    @Override
    public void deleteProject(long projectId) {
        projectMemberRepository.deleteByProject_ProjectId(projectId);
    }
}
