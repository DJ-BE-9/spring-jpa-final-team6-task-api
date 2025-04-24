package com.nhnacademy.service.impl;

import com.nhnacademy.exception.MilestoneNotFoundException;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.repository.MilestoneRepository;
import com.nhnacademy.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MilestoneServiceImpl implements MilestoneService {
    private final MilestoneRepository milestoneRepository;


    @Override
    public Milestone registerMilestone(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    @Override
    public Milestone updateMilestone(long milestoneId, Milestone milestone) {
        Milestone m = milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException("milestone not found id: " + milestoneId));
        m.setMilestoneName(milestone.getMilestoneName());
        m.setMilestoneStartedAt(milestone.getMilestoneStartedAt());
        m.setMilestoneEndedAt( milestone.getMilestoneEndedAt());
        return milestoneRepository.save(m);
    }

    @Override
    public void deleteMilestone(long milestoneId) {
        Milestone m = milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException("milestone not found id: " + milestoneId));
        milestoneRepository.deleteById(milestoneId);
    }

    @Override
    public List<Milestone> getMilestonesByProjectId(long projectId) {
        return milestoneRepository.findAllByProject_ProjectId(projectId);
    }
}
