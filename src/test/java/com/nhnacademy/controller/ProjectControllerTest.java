package com.nhnacademy.controller;

import com.nhnacademy.model.ResponseDto;
import com.nhnacademy.model.project.dto.RegisterProjectRequest;
import com.nhnacademy.model.project.dto.ResponseProjectDto;
import com.nhnacademy.model.project.dto.ResponseProjectIdDto;
import com.nhnacademy.model.project.dto.UpdateProjectRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.project.type.State;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;
import com.nhnacademy.service.ProjectMemberService;
import com.nhnacademy.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private ProjectMemberService projectMemberService;

    @Test
    void testRegisterProject() throws Exception {
        when(projectService.registerProject(any(RegisterProjectRequest.class)))
                .thenReturn(1L);

        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "projectName": "New Project",
                                    "projectDescription": "Project Description",
                                    "state": "ACTIVE"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.projectId").value(1L));
    }

    @Test
    void testRegisterProjectMember() throws Exception {
        mockMvc.perform(post("/project/1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userId": "memberId",
                                    "role": "ROLE_MEMBER"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("프로젝트 멤버 등록"));

        verify(projectMemberService).registerMemberByProject(eq(1L), any(RegisterProjectMemberRequest.class));
    }


    @Test
    void testUpdateProject() throws Exception {
        mockMvc.perform(put("/project/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "projectName": "Updated Project",
                                    "projectDescription": "Updated Description",
                                    "state": "DORMANT"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("Project updated!"));

        verify(projectService).updateProject(eq(1L), any(UpdateProjectRequest.class));
    }

    @Test
    void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/project/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Project deleted!"));

        verify(projectMemberService).deleteProject(1L);
        verify(projectService).deleteProject(1L);
    }
}