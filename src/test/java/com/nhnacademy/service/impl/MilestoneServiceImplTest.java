package com.nhnacademy.service.impl;

import com.nhnacademy.exception.milestone.MilestoneNameAlreadyExistsException;
import com.nhnacademy.exception.milestone.MilestoneNotFoundException;
import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.dto.ResponseGetMilestonesDto;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.repository.MilestoneRepository;
import com.nhnacademy.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MilestoneServiceImplTest {

    @Mock
    private MilestoneRepository milestoneRepository;

    @Mock
    private ProjectRepository projectRepository;

    private MilestoneServiceImpl milestoneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        milestoneService = new MilestoneServiceImpl(milestoneRepository, projectRepository);
    }

    @Test
    void registerMilestone() {
        long projectId = 1L;
        RegisterMilestoneRequest request = new RegisterMilestoneRequest("New Milestone", ZonedDateTime.now(), ZonedDateTime.now());
        Project project = new Project();
        Milestone milestone = new Milestone(request.getMilestoneName(), request.getMilestoneStartedAt(), request.getMilestoneEndedAt(), project);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(milestoneRepository.existsMilestoneByMilestoneNameAndProject_ProjectId(request.getMilestoneName(), projectId)).thenReturn(false);
        when(milestoneRepository.save(any(Milestone.class))).thenReturn(milestone);

        Milestone result = milestoneService.registerMilestone(request, projectId);

        assertEquals(milestone.getMilestoneName(), result.getMilestoneName());
        assertEquals(milestone.getMilestoneStartedAt(), result.getMilestoneStartedAt());
        assertEquals(milestone.getMilestoneEndedAt(), result.getMilestoneEndedAt());
    }

    @Test
    void registerMilestone_ProjectNotFound() {
        long projectId = 1L;
        RegisterMilestoneRequest request = new RegisterMilestoneRequest("New Milestone", ZonedDateTime.now(), ZonedDateTime.now());

        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> milestoneService.registerMilestone(request, projectId));
    }

    @Test
    void registerMilestone_NameAlreadyExists() {
        long projectId = 1L;
        RegisterMilestoneRequest request = new RegisterMilestoneRequest("Duplicate Milestone", ZonedDateTime.now(), ZonedDateTime.now());
        Project project = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(milestoneRepository.existsMilestoneByMilestoneNameAndProject_ProjectId(request.getMilestoneName(), projectId)).thenReturn(true);

        assertThrows(MilestoneNameAlreadyExistsException.class, () -> milestoneService.registerMilestone(request, projectId));
    }

    @Test
    void updateMilestone() {
        long milestoneId = 1L;
        Milestone originalMilestone = new Milestone("Old Name", ZonedDateTime.now(), ZonedDateTime.now(), new Project());
        Milestone updatedMilestone = new Milestone(milestoneId, "New Name", new Project(), ZonedDateTime.now(), ZonedDateTime.now());

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.of(originalMilestone));
        when(milestoneRepository.save(any(Milestone.class))).thenReturn(originalMilestone);

        Milestone result = milestoneService.updateMilestone(milestoneId, updatedMilestone);

        assertEquals("New Name", result.getMilestoneName());
    }

    @Test
    void updateMilestone_NotFound() {
        long milestoneId = 1L;
        Milestone updatedMilestone = new Milestone(milestoneId, "New Name", new Project(), ZonedDateTime.now(), ZonedDateTime.now());

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.empty());

        assertThrows(MilestoneNotFoundException.class, () -> milestoneService.updateMilestone(milestoneId, updatedMilestone));
    }

    @Test
    void deleteMilestone() {
        long milestoneId = 1L;
        Milestone milestone = new Milestone("Delete Me", ZonedDateTime.now(), ZonedDateTime.now(), new Project());

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.of(milestone));

        milestoneService.deleteMilestone(milestoneId);

        verify(milestoneRepository).deleteById(milestoneId);
    }

    @Test
    void deleteMilestone_NotFound() {
        long milestoneId = 1L;

        when(milestoneRepository.findById(milestoneId)).thenReturn(Optional.empty());

        assertThrows(MilestoneNotFoundException.class, () -> milestoneService.deleteMilestone(milestoneId));
    }

    @Test
    void getMilestonesbyProjectId() {
        long projectId = 1L;
        Milestone milestone1 = new Milestone("Milestone 1", ZonedDateTime.now(), ZonedDateTime.now(), new Project());
        Milestone milestone2 = new Milestone("Milestone 2", ZonedDateTime.now(), ZonedDateTime.now(), new Project());

        when(milestoneRepository.findAllByProject_ProjectId(projectId)).thenReturn(List.of(milestone1, milestone2));

        ResponseGetMilestonesDto result = milestoneService.getMilestonesbyProjectId(projectId);

        assertEquals(2, result.getMilestones().size());
        assertEquals("Milestone 1", result.getMilestones().get(0).getMilestoneName());
        assertEquals("Milestone 2", result.getMilestones().get(1).getMilestoneName());
    }
}
