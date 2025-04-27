package com.nhnacademy.controller;

import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.ResponseProjectDto;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;
import com.nhnacademy.service.ProjectMemberService;
import com.nhnacademy.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
@Slf4j
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    // project 생성
    @PostMapping
    public ResponseEntity<Project> registerProject(@RequestBody RegisterProjectRequest registerProjectRequest) {
        log.info("Registering project: " + registerProjectRequest.getProjectName());
        log.info("Registering project: " + registerProjectRequest.getProjectState());
        Project project = projectService.registerProject(registerProjectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    // 해당 프로젝트에 멤버 등록
    @PostMapping("/{projectId}/members")
    public ResponseEntity<Project> registerProject(@PathVariable long projectId, @RequestBody RegisterProjectMemberRequest request) {
        Project project = projectMemberService.registerMemberByProject(projectId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    // 회원이 등록한 프로젝트 목록
    @GetMapping("/members/{userId}")
    public ResponseEntity<ResponseProjectDto> getProjects(@PathVariable String userId) {
        List<Project> projects = projectService.getAllProjectsByUserId(userId);
        for(Project p : projects) {
            log.error("{}", p);
        }
        ResponseProjectDto projectDto = new ResponseProjectDto(projects);
        return ResponseEntity.status(HttpStatus.OK).body(projectDto);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable long projectId) {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<String> updateProject(@PathVariable long projectId,
                                                @RequestBody UpdateProjectRequest updateProjectRequest) {
        projectService.updateProject(projectId,updateProjectRequest);
        return ResponseEntity.ok("Project updated!");
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable long projectId) {
        projectMemberService.deleteProject(projectId);
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project deleted!");
    }


}
