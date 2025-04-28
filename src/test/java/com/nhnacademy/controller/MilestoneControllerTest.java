package com.nhnacademy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.model.milestone.dto.RegisterMilestoneRequest;
import com.nhnacademy.model.milestone.dto.UpdateMilestoneRequest;
import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.service.MilestoneService;
import com.nhnacademy.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MilestoneService milestoneService;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMilestones() throws Exception {
        long projectId = 1L;

        mockMvc.perform(get("/project/{projectId}/milestone", projectId))
                .andExpect(status().isOk());
    }

    @Test
    void registerMilestoneByProject() throws Exception {
        long projectId = 1L;

        RegisterMilestoneRequest request = new RegisterMilestoneRequest("New Milestone", ZonedDateTime.now(), ZonedDateTime.now());

        // 프로젝트가 존재하는 경우 mocking
        Mockito.when(projectService.getProjectById(projectId)).thenReturn(new Project());

        Mockito.when(milestoneService.registerMilestone(any(RegisterMilestoneRequest.class), eq(projectId)))
                .thenReturn(new Milestone());

        mockMvc.perform(post("/project/{projectId}/milestone", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateMilestoneByProject() throws Exception {
        long projectId = 1L;
        long milestoneId = 1L;

        UpdateMilestoneRequest request = new UpdateMilestoneRequest("Updated Milestone", ZonedDateTime.now(), ZonedDateTime.now());

        Mockito.when(projectService.getProjectById(projectId)).thenReturn(new Project());

        Mockito.when(milestoneService.updateMilestone(eq(milestoneId), any(Milestone.class)))
                .thenReturn(new Milestone());

        mockMvc.perform(put("/project/{projectId}/milestone/{milestoneId}", projectId, milestoneId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMilestoneByProject() throws Exception {
        long projectId = 1L;
        long milestoneId = 1L;

        mockMvc.perform(delete("/project/{projectId}/milestone/{milestoneId}", projectId, milestoneId))
                .andExpect(status().isOk());
    }
}
