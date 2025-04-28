package com.nhnacademy.service.impl;

import com.nhnacademy.exception.projectTag.ProjectTagAlreadyExistsException;
import com.nhnacademy.exception.projectTag.ProjectTagNotFoundException;
import com.nhnacademy.exception.tag.TagNotFoundException;
import com.nhnacademy.exception.task.TaskNotFoundException;
import com.nhnacademy.model.projectTag.dto.ProjectTagByTagNameResponse;
import com.nhnacademy.model.projectTag.dto.ProjectTagRegisterRequest;
import com.nhnacademy.model.projectTag.entity.ProjectTag;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.repository.ProjectTagRepository;
import com.nhnacademy.repository.TagRepository;
import com.nhnacademy.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectTagServiceImplTest {

    @Mock
    private ProjectTagRepository projectTagRepository;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TagRepository tagRepository;

    private ProjectTagServiceImpl projectTagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectTagService = new ProjectTagServiceImpl(projectTagRepository, taskRepository, tagRepository);
    }

    @Test
    void findByTaskId() {
        long projectTagId = 1L;
        ProjectTag projectTag = new ProjectTag(new Tag(), new Task());

        when(projectTagRepository.findById(projectTagId)).thenReturn(Optional.of(projectTag));

        ProjectTag result = projectTagService.findByTaskId(projectTagId);

        assertEquals(projectTag, result);
    }

    @Test
    void findByTaskId_NotFound() {
        long projectTagId = 1L;
        when(projectTagRepository.findById(projectTagId)).thenReturn(Optional.empty());

        assertThrows(ProjectTagNotFoundException.class, () -> projectTagService.findByTaskId(projectTagId));
    }

    @Test
    void findTagNameByTaskId() {
        long taskId = 1L;
        Tag tag1 = new Tag();
        tag1.setTagName("태그1");
        Tag tag2 = new Tag();
        tag2.setTagName("태그2");

        ProjectTag projectTag1 = new ProjectTag(tag1, new Task());
        ProjectTag projectTag2 = new ProjectTag(tag2, new Task());

        when(taskRepository.existsById(taskId)).thenReturn(true);
        when(projectTagRepository.findAllByTask_TaskId(taskId)).thenReturn(List.of(projectTag1, projectTag2));

        List<ProjectTagByTagNameResponse> result = projectTagService.findTagNameByTaskId(taskId);

        assertEquals(2, result.size());
        assertEquals("태그1", result.get(0).getTagName());
        assertEquals("태그2", result.get(1).getTagName());
    }

    @Test
    void findTagNameByTaskId_TaskNotFound() {
        long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> projectTagService.findTagNameByTaskId(taskId));
    }

    @Test
    void registerProjectTag() {
        long tagId = 1L;
        long taskId = 1L;
        ProjectTagRegisterRequest request = new ProjectTagRegisterRequest(tagId, taskId);

        Tag tag = new Tag();
        Task task = new Task();
        ProjectTag projectTag = new ProjectTag(tag, task);

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(projectTagRepository.existsByTag_TagIdAndTask_TaskId(tagId, taskId)).thenReturn(false);
        when(projectTagRepository.save(any(ProjectTag.class))).thenReturn(projectTag);

        ProjectTag result = projectTagService.registerProjectTag(request);

        assertEquals(projectTag, result);
    }

    @Test
    void registerProjectTag_TagNotFound() {
        long tagId = 1L;
        long taskId = 1L;
        ProjectTagRegisterRequest request = new ProjectTagRegisterRequest(tagId, taskId);

        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> projectTagService.registerProjectTag(request));
    }

    @Test
    void registerProjectTag_TaskNotFound() {
        long tagId = 1L;
        long taskId = 1L;
        ProjectTagRegisterRequest request = new ProjectTagRegisterRequest(tagId, taskId);

        Tag tag = new Tag();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> projectTagService.registerProjectTag(request));
    }

    @Test
    void registerProjectTag_AlreadyExists() {
        long tagId = 1L;
        long taskId = 1L;
        ProjectTagRegisterRequest request = new ProjectTagRegisterRequest(tagId, taskId);

        Tag tag = new Tag();
        Task task = new Task();

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(projectTagRepository.existsByTag_TagIdAndTask_TaskId(tagId, taskId)).thenReturn(true);

        assertThrows(ProjectTagAlreadyExistsException.class, () -> projectTagService.registerProjectTag(request));
    }

    @Test
    void delete() {
        long projectTagId = 1L;
        when(projectTagRepository.existsById(projectTagId)).thenReturn(true);

        projectTagService.delete(projectTagId);

        verify(projectTagRepository).deleteById(projectTagId);
    }

    @Test
    void delete_NotFound() {
        long projectTagId = 1L;
        when(projectTagRepository.existsById(projectTagId)).thenReturn(false);

        assertThrows(ProjectTagNotFoundException.class, () -> projectTagService.delete(projectTagId));
    }
}