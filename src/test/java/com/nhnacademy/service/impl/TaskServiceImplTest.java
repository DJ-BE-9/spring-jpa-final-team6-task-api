package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.task.TaskAlreadyExistsException;
import com.nhnacademy.exception.task.TaskNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ProjectRepository projectRepository;

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(taskRepository, projectRepository);
    }

    @Test
    void existsTag() {
        long tagId = 1L;
        when(taskRepository.existsById(tagId)).thenReturn(true);

        boolean result = taskService.existsTag(tagId);

        assertTrue(result);
    }

    @Test
    void getTaskById() {
        long taskId = 1L;
        Task task = new Task("테스트 태스크", "테스트 설명", new Project());

        when(taskRepository.existsById(taskId)).thenReturn(true);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
    }

    @Test
    void getTaskById_NotFound() {
        long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    void getAllTasksByProjectId() {
        long projectId = 1L;
        Project project = new Project();
        List<Task> tasks = List.of(new Task("태스크1", "설명1", project));

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(taskRepository.findAllByProject(project)).thenReturn(tasks);

        List<Task> result = taskService.getAllTasksByProjectId(projectId);

        assertEquals(tasks, result);
    }

    @Test
    void save() {
        long projectId = 1L;
        TaskRegisterRequest request = new TaskRegisterRequest("새 태스크", "새 설명");
        Project project = new Project();
        Task task = new Task("새 태스크", "새 설명", project);

        when(taskRepository.existsByTaskTitle(request.getTaskTitle())).thenReturn(false);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task result = taskService.save(request, projectId);

        assertEquals(task, result);
    }

    @Test
    void save_TitleAlreadyExists() {
        long projectId = 1L;
        TaskRegisterRequest request = new TaskRegisterRequest("이미 있는 태스크", "설명");

        when(taskRepository.existsByTaskTitle(request.getTaskTitle())).thenReturn(true);

        assertThrows(TaskAlreadyExistsException.class, () -> taskService.save(request, projectId));
    }

    @Test
    void updateTask() {
        long taskId = 1L;
        TaskUpdateRequest request = new TaskUpdateRequest("수정된 태스크", "수정된 설명");
        Task task = new Task("원래 태스크", "원래 설명", new Project());

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        Task result = taskService.updateTask(taskId, request);

        assertEquals("수정된 태스크", result.getTaskTitle());
        assertEquals("수정된 설명", result.getTaskDescription());
        verify(taskRepository).save(task);
    }

    @Test
    void deleteTask() {
        long taskId = 1L;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository).deleteById(taskId);
    }
}