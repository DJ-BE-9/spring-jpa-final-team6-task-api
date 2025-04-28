package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.EmptyRequestException;
import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.projectMember.AlreadyExistsMemberException;
import com.nhnacademy.model.project.dto.ProjectIdRequest;
import com.nhnacademy.model.project.dto.ProjectMemberRequest;
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

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ProjectMemberRequest> getProjectMembers(long projectId) {
        if(projectId < 0) {
            throw new EmptyRequestException("Project 값을 찾지 못했습니다.");
        }

        List<ProjectMember> projectMembers = projectMemberRepository.findAllByProject_ProjectId(projectId);
        List<ProjectMemberRequest> list = new ArrayList<>();

        for(int i = 0; i < projectMembers.size(); i++) {
            list.add(new ProjectMemberRequest(projectMembers.get(i).getUserId(), projectMembers.get(i).isProjectManager()));
        }

        return list;
    }

    @Override
    public void deleteProjectMemberByUserId(String memberId, long projectId) {
        if(Objects.isNull(memberId)) {
            throw new EmptyRequestException("member ID 값을 받지 못했습니다.");
        }

        projectMemberRepository.deleteProjectMemberByUserIdAndProject_ProjectId(memberId, projectId);
    }

    @Override
    public void registerMemberProject(long projectId, RegisterProjectMemberRequest request) {
        if(Objects.isNull(request) || Objects.isNull(request.isProjectManager()) || Objects.isNull(request.getMemberId())) {
            throw new IllegalArgumentException();
        }

        Project project = projectRepository.findById(projectId).orElseThrow( () -> new ProjectNotFoundException(projectId));

        List<ProjectMember> projectMembers = projectMemberRepository.findAllByProject_ProjectId(projectId);
        for(int i = 0; i < projectMembers.size(); i++) {
            if(projectMembers.get(i).getUserId().equals(request.getMemberId())) {
                throw new AlreadyExistsMemberException("해당 Member는 이미 해당 프로젝트에 존재합니다.");
            }
        }

        ProjectMember projectMember = new ProjectMember(project, request.getMemberId(), request.isProjectManager());
        projectMemberRepository.save(projectMember);

    }
}
