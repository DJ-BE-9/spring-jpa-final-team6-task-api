package com.nhnacademy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.model.projectTag.dto.RegisterProjectTagRequest;
import com.nhnacademy.model.tag.dto.ResponseGetTagsDto;
import com.nhnacademy.service.ProjectTagService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectTagController.class)
class ProjectTagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectTagService projectTagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerTaskTag() throws Exception {
        long projectId = 1L;
        long taskId = 1L;

        RegisterProjectTagRequest request = new RegisterProjectTagRequest(); // 빈 요청 객체 (테스트용)

        mockMvc.perform(post("/project/{projectId}/task/{taskId}/projectTag", projectId, taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        Mockito.verify(projectTagService).registerProjectTagList(any(RegisterProjectTagRequest.class), eq(taskId));
    }

    @Test
    void getProjectTag() throws Exception {
        long projectId = 1L;
        long taskId = 1L;

        ResponseGetTagsDto response = new ResponseGetTagsDto(); // 빈 결과 객체 (테스트용)

        Mockito.when(projectTagService.findTagNameByTaskId(taskId)).thenReturn(response);

        mockMvc.perform(get("/project/{projectId}/task/{taskId}/projectTag", projectId, taskId))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTaskTag() throws Exception {
        long projectId = 1L;
        long taskId = 1L;
        long projectTagId = 1L;

        mockMvc.perform(delete("/project/{projectId}/task/{taskId}/projectTag/{projectTagId}", projectId, taskId, projectTagId))
                .andExpect(status().isOk());

        Mockito.verify(projectTagService).delete(projectTagId);
    }

    @Test
    void deleteProjectTag() throws Exception {
        long projectId = 1L;
        long taskId = 1L;

        mockMvc.perform(delete("/project/{projectId}/task/{taskId}/projectTag", projectId, taskId))
                .andExpect(status().isOk());

        Mockito.verify(projectTagService).deleteByTaskId(taskId);
    }
}
