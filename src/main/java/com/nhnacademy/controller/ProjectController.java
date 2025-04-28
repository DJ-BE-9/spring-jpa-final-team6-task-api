package com.nhnacademy.controller;

import com.nhnacademy.model.ResponseDto;
import com.nhnacademy.model.project.dto.*;
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

    //project 생성
    @PostMapping
    public ResponseEntity<ResponseProjectIdDto> registerProject(@RequestBody RegisterProjectRequest registerProjectRequest) {
        log.info("registerProject : {}", registerProjectRequest.getProjectName());

        long projectId = projectService.registerProject(registerProjectRequest);

        ResponseProjectIdDto response = new ResponseProjectIdDto(projectId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 해당 프로젝트에 멤버 등록
    @PostMapping("/{projectId}/members")
    public ResponseEntity<ResponseDto> registerProject(@PathVariable long projectId, @RequestBody RegisterProjectMemberRequest request) {
        projectMemberService.registerMemberProject(projectId, request);
        ResponseDto projectMemberResponseDto = new ResponseDto(request.getMemberId(), "프로젝트 멤버 등록");
        log.info("프로젝트 멤버 등록");
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberResponseDto);
    }

    // 해당 프로젝트 멤버 리스트
    @GetMapping("/{projectId}/members")
    public ResponseEntity<ResponseProjectMembersDto> getProjectMembers(@PathVariable("projectId") long projectId) {
        List<ProjectMemberRequest> teamMembers = projectMemberService.getProjectMembers(projectId);

        ResponseProjectMembersDto responseProjectMembersDto = new ResponseProjectMembersDto(teamMembers);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseProjectMembersDto);
    }

    // 프로젝트 멤버 삭제
    @PostMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<ResponseDto> postDeleteProjectMember(@PathVariable("projectId") long projectId,
                                                               @PathVariable("memberId") String memberId) {
        projectMemberService.deleteProjectMemberByUserId(memberId, projectId);

        ResponseDto responseDto = new ResponseDto(memberId, "member 삭제 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/{projectId}/members/{memberId}/state")
    public ResponseEntity<String> postProjectState(@PathVariable("projectId") long projectId,
                                                               @PathVariable("memberId") String memberId,
                                                               @RequestBody ProjectStateRequest projectStateRequest) {
        projectService.stateProject(projectStateRequest.getProjectState(), projectStateRequest.getProjectId());

        return ResponseEntity.status(HttpStatus.CREATED).body("state update");
    }

    // 회원이 등록한 프로젝트 목록
    @GetMapping("/members/{memberId}")
    public ResponseEntity<ResponseProjectDto> getProjects(@PathVariable String memberId) {
        List<Project> projects = projectService.getAllProjectsByUserId(memberId);
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
