package com.nhnacademy.controller;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.dto.ResponseGetMilestoneDto;
import com.nhnacademy.model.milestone.dto.ResponseGetMilestonesDto;
import com.nhnacademy.model.milestone.dto.UpdateMilestoneRequest;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.service.MilestoneService;
import com.nhnacademy.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
@Slf4j
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final ProjectService projectService;

    // 해당 프로젝트의 milestone 목록 반환
    @GetMapping("/{projectId}/milestone")
    public ResponseEntity<ResponseGetMilestonesDto> getMilestones(@PathVariable Long projectId) {
        ResponseGetMilestonesDto response =  milestoneService.getMilestonesbyProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Milestone 등록
    @PostMapping("/{projectId}/milestone")
    public ResponseEntity<ResponseGetMilestoneDto> registerMilestoneByProject(@PathVariable long projectId,
                                                                              @RequestBody RegisterMilestoneRequest request) {
        log.info("{}", projectId);
        log.info("{}", request.getMilestoneName());
        log.info("{}", request.getMilestoneStartedAt());
        log.info("{}", request.getMilestoneEndedAt());
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException(projectId);
        }
        Milestone milestone = milestoneService.registerMilestone(request,projectId);
        ResponseGetMilestoneDto response = new ResponseGetMilestoneDto(milestone.getMilestoneId(), milestone.getMilestoneName(), milestone.getMilestoneStartedAt(), milestone.getMilestoneEndedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{projectId}/milestone/{milestoneId}")
    public ResponseEntity<ResponseGetMilestoneDto> updateMilestoneByProject(@PathVariable long projectId,
                                       @PathVariable long milestoneId,
                                       @RequestBody UpdateMilestoneRequest request) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException(projectId);
        }
        Milestone milestone = milestoneService.updateMilestone(milestoneId,  new Milestone(milestoneId, request.getMilestoneName(), project, request.getMilestoneStartedAt(), request.getMilestoneEndedAt()));
        ResponseGetMilestoneDto response = new ResponseGetMilestoneDto(milestone.getMilestoneId(), milestone.getMilestoneName(), milestone.getMilestoneStartedAt(), milestone.getMilestoneEndedAt());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{projectId}/milestone/{milestoneId}")
    public void deleteMilestoneByProject(@PathVariable long milestoneId, @PathVariable long projectId) {
        milestoneService.deleteMilestone(milestoneId);
    }

}
