package com.nhnacademy.service.impl;

import com.nhnacademy.exception.milestone.MilestoneNameAlreadyExistsException;
import com.nhnacademy.exception.milestone.MilestoneNotFoundException;
import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.dto.ResponseGetMilestoneDto;
import com.nhnacademy.model.milestone.dto.ResponseGetMilestonesDto;
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
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
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
        m.setMilestoneEndedAt(milestone.getMilestoneEndedAt());
        return milestoneRepository.save(m);
    }

    @Override
    public void deleteMilestone(long milestoneId) {
        Milestone m = milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException("milestone not found id: " + milestoneId));
        milestoneRepository.deleteById(milestoneId);
    }

    @Override
    public ResponseGetMilestonesDto getMilestonesbyProjectId(long projectId) {
        List<Milestone> milestoneList =  milestoneRepository.findAllByProject_ProjectId(projectId);
        ResponseGetMilestonesDto dtoList = new ResponseGetMilestonesDto();
        if(!milestoneList.isEmpty()) {
            for(Milestone milestone : milestoneList) {
                ResponseGetMilestoneDto dto = new ResponseGetMilestoneDto(milestone.getMilestoneId(), milestone.getMilestoneName(), milestone.getMilestoneStartedAt(), milestone.getMilestoneEndedAt());
                dtoList.getMilestones().add(dto);
            }
        }

        return dtoList;
    }

    @Override
    public ResponseGetMilestoneDto getMilestonebyMilestoneId(long milestoneId) {
        Milestone m = milestoneRepository.findById(milestoneId).orElseThrow(() -> new MilestoneNotFoundException("milestone not found id: " + milestoneId));
        ResponseGetMilestoneDto response = new ResponseGetMilestoneDto(
                m.getMilestoneId(),
                m.getMilestoneName(),
                m.getMilestoneStartedAt(),
                m.getMilestoneEndedAt()
        );
        return response;
    }
}
