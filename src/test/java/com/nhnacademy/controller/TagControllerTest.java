package com.nhnacademy.controller;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.service.ProjectService;
import com.nhnacademy.service.TagService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @MockBean
    private ProjectService projectService;

    @Test
    void testGetTag() throws Exception {
        long projectId = 1L;
        long tagId = 1L;
        Tag tag = new Tag();
        tag.setTagName("Sample Tag");

        when(tagService.findByTagId(tagId)).thenReturn(tag);

        mockMvc.perform(get("/project/{projectId}/tag/{tagId}", projectId, tagId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tagName").value("Sample Tag"));
    }

    @Test
    void testGetTags() throws Exception {
        long projectId = 1L;
        Project project = new Project();
        Tag tag = new Tag();
        tag.setTagName("Sample Tag");

        when(projectService.getProjectById(projectId)).thenReturn(project);
        when(tagService.findAllByProjectId(project)).thenReturn(List.of(tag));

        mockMvc.perform(get("/project/{projectId}/tag", projectId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tagName").value("Sample Tag"));
    }

    @Test
    void testRegisterTag() throws Exception {
        long projectId = 1L;
        TagRegisterRequest request = new TagRegisterRequest("New Tag");
        Tag tag = new Tag("New Tag", new Project());

        when(tagService.registerTag(any(TagRegisterRequest.class), eq(projectId))).thenReturn(tag);

        mockMvc.perform(post("/project/{projectId}/tag", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tagName": "New Tag"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tagName").value("New Tag"));
    }

    @Test
    void testUpdateTag() throws Exception {
        long projectId = 1L;
        long tagId = 1L;
        Tag tag = new Tag("New Tag", new Project());

        TagUpdateRequest request = new TagUpdateRequest("Updated Tag");

        when(tagService.updateTag(eq(tagId), any(TagUpdateRequest.class))).thenReturn(tag);

        mockMvc.perform(put("/project/{projectId}/tag/{tagId}", projectId, tagId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "tagName": "Updated Tag"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(tagId + " updated"));
    }

    @Test
    void testDeleteTag() throws Exception {
        long projectId = 1L;
        long tagId = 1L;

        doNothing().when(tagService).delete(tagId);

        mockMvc.perform(delete("/project/{projectId}/tag/{tagId}", projectId, tagId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Tag " + tagId + " deleted successfully"));
    }
}