package com.nhnacademy.service;

import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.entity.Milestone;

import java.util.List;

public interface MilestoneService {
    Milestone registerMilestone(RegisterMilestoneRequest request, long projectId);
    Milestone updateMilestone(long milestoneId, Milestone milestone);
    void deleteMilestone(long milestoneId);
    List<Milestone> getMilestonesbyProjectId(long projectId);
}
