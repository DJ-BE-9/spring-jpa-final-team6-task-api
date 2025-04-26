package com.nhnacademy.controller;

import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;
import com.nhnacademy.model.tag.dto.ResponseGetProjectsDto;
import com.nhnacademy.service.ProjectMemberService;
import com.nhnacademy.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;

    // project 생성
    @PostMapping
    public ResponseEntity<String> registerProject(@RequestBody RegisterProjectRequest registerProjectRequest) {
        log.info("registerProjectRequest:{} {}", registerProjectRequest.getProjectName(), registerProjectRequest.getProjectState());

        projectService.registerProject(registerProjectRequest);
        return ResponseEntity.ok("Project registered!");
    }

    // 해당 프로젝트에 멤버 등록
    @PostMapping("/{memberId}")
    public ResponseEntity<Project> registerProject(@PathVariable String memberId, @RequestBody RegisterProjectMemberRequest request) {
        Project project = projectMemberService.registerMemberByProject(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    // 회원이 등록한 프로젝트 목록
    @GetMapping("/{memberId}")
    public ResponseEntity<ResponseGetProjectsDto> getProjects(@PathVariable String memberId) {
        List<Project> projects = projectService.getAllProjectsByUserId(memberId);

        ResponseGetProjectsDto responseGetProjectsDto = new ResponseGetProjectsDto(projects);

        return ResponseEntity.status(HttpStatus.OK).body(responseGetProjectsDto);
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
