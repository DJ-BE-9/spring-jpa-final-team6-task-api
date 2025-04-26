package com.nhnacademy.controller;

import com.nhnacademy.exception.ProjectNotFoundException;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.dto.UpdateMilestoneRequest;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.service.MilestoneService;
import com.nhnacademy.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final ProjectService projectService;

    // 해당 프로젝트의 milestone 목록 반환
    @GetMapping("/{projectId}/milestone")
    public List<Milestone> getMilestones(@PathVariable Long projectId) {
        return milestoneService.getMilestonesbyProjectId(projectId);

    }

    // Milestone 등록
    @PostMapping("/{projectId}/milestone")
    public Milestone registerMilestoneByProject(@PathVariable long projectId,
                                    @RequestBody RegisterMilestoneRequest request) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("project not found");
        }
        Milestone milestone = new Milestone(request.getMilestoneName(),request.getMilestoneStartedAt(), request.getMilestoneEndedAt(), project);
        return milestoneService.registerMilestone(milestone);
    }

    @PutMapping("/{projectId}/milestone/{milestoneId}")
    public Milestone updateMilestoneByProject(@PathVariable long projectId,
                                       @PathVariable long milestoneId,
                                       @RequestBody UpdateMilestoneRequest request) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("project not found");
        }
        Milestone milestone = new Milestone(milestoneId,  request.getMilestoneName(), project, request.getMilestoneStartedAt(), request.getMilestoneEndedAt());
        return milestoneService.updateMilestone(milestoneId, milestone);
    }

    @DeleteMapping("/{projectId}/milestone/{milestoneId}")
    public void deleteMilestoneByProject(@PathVariable long milestoneId, @PathVariable long projectId) {
        milestoneService.deleteMilestone(milestoneId);
    }

}
