package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.project.type.State;
import com.nhnacademy.repository.ProjectMemberRepository;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public boolean existsProject(long projectId) {
        return projectRepository.existsById(projectId);
    }

    @Override
    public void registerProject(RegisterProjectRequest registerProjectRequest) {
//        if(Objects.isNull(registerProjectRequest) || Objects.isNull(registerProjectRequest.getProjectState()) || Objects.isNull(registerProjectRequest.getProjectName()) ) {
//            throw new IllegalArgumentException();
//        }
        String projectName = registerProjectRequest.getProjectName();
//        State projectState = State.valueOf(registerProjectRequest.getProjectState());

        Project project = new Project(registerProjectRequest.getProjectName(), State.ACTIVE);
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        if(!existsProject(projectId)) {
            throw new ProjectNotFoundException(projectId);
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public void updateProject(Long projectId,UpdateProjectRequest updateProjectRequest) {
        if(Objects.isNull(projectId) || Objects.isNull(updateProjectRequest) || Objects.isNull(updateProjectRequest.getProjectName()) || Objects.isNull(updateProjectRequest.getProjectState()) ) {
            throw new IllegalArgumentException();
        }
        String projectName = updateProjectRequest.getProjectName();
        State projectState = State.valueOf(updateProjectRequest.getProjectState());

        Project project = new Project(projectId, projectName, projectState);
        projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjectsByUserId(String memberId) {
        log.error(memberId);
        return projectMemberRepository.findAllProjectByUserId(memberId);
    }

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
    }


}
