package com.nhnacademy.service.impl;

import com.nhnacademy.exception.MilestoneNameAlreadyExistsException;
import com.nhnacademy.exception.MilestoneNotFoundException;
import com.nhnacademy.exception.ProjectNotFoundException;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.repository.MilestoneRepository;
import com.nhnacademy.repository.ProjectRepository;
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
    private final ProjectRepository projectRepository;


    @Override
    public Milestone registerMilestone(RegisterMilestoneRequest request, long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId + " not found"));
        if(milestoneRepository.existsMilestoneByMilestoneNameAndProject_ProjectId(request.getMilestoneName(), projectId)) {
            throw new MilestoneNameAlreadyExistsException(request.getMilestoneName());
        }
        Milestone milestone = new Milestone(request.getMilestoneName(), request.getMilestoneStartedAt(),request.getMilestoneEndedAt(), project);
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
