package com.nhnacademy.service;

import com.nhnacademy.model.milestone.entity.Milestone;

public interface MilestoneService {
    Milestone registerMilestone(Milestone milestone);
    Milestone updateMilestone(long milestoneId, Milestone milestone);
    void deleteMilestone(long milestoneId);
}
