package com.nhnacademy.controller;

import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void testGetTask() throws Exception {
        long projectId = 1L;
        long taskId = 1L;

        Task mockTask = new Task(taskId, "Test Task", "Task Description", null);

        when(taskService.getTaskById(taskId)).thenReturn(mockTask);

        mockMvc.perform(get("/project/{projectId}/task/{taskId}", projectId, taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId").value(taskId))
                .andExpect(jsonPath("$.taskTitle").value("Test Task"))
                .andExpect(jsonPath("$.taskDescription").value("Task Description"));

        verify(taskService).getTaskById(taskId);
    }

    @Test
    void testGetTasks() throws Exception {
        long projectId = 1L;

        List<Task> tasks = List.of(
                new Task(1L, "Task 1", "Description 1", null),
                new Task(2L, "Task 2", "Description 2", null)
        );

        when(taskService.getAllTasksByProjectId(projectId)).thenReturn(tasks);

        mockMvc.perform(get("/project/{projectId}/task", projectId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].taskTitle").value("Task 1"))
                .andExpect(jsonPath("$[1].taskTitle").value("Task 2"));

        verify(taskService).getAllTasksByProjectId(projectId);
    }

    @Test
    void testRegisterTask() throws Exception {
        long projectId = 1L;

        TaskRegisterRequest request = new TaskRegisterRequest("New Task", "Task description");
        Task mockTask = new Task(1L, "New Task", "Task description", null);

        when(taskService.save(any(TaskRegisterRequest.class), eq(projectId))).thenReturn(mockTask);

        mockMvc.perform(post("/project/{projectId}/task", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "taskTitle": "New Task",
                                    "taskDescription": "Task description"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.taskId").value(1L))
                .andExpect(jsonPath("$.taskTitle").value("New Task"))
                .andExpect(jsonPath("$.taskDescription").value("Task description"));

        verify(taskService).save(any(TaskRegisterRequest.class), eq(projectId));
    }

    @Test
    void testUpdateTask() throws Exception {
        Task mockTask = new Task(1L, "New Task", "Task description", null);

        long projectId = 1L;
        long taskId = 1L;

        TaskUpdateRequest request = new TaskUpdateRequest("Updated Task", "Updated description");

        when(taskService.updateTask(taskId, request)).thenReturn(mockTask);

        mockMvc.perform(put("/project/{projectId}/task/{taskId}", projectId, taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "taskTitle": "Updated Task",
                                    "taskDescription": "Updated description"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("Task updated"));

        ArgumentCaptor<TaskUpdateRequest> captor = ArgumentCaptor.forClass(TaskUpdateRequest.class);
        verify(taskService).updateTask(eq(taskId), captor.capture());

        TaskUpdateRequest capturedRequest = captor.getValue();
        assert capturedRequest.getTaskTitle().equals("Updated Task");
        assert capturedRequest.getTaskDescription().equals("Updated description");
    }

    @Test
    void testDeleteTask() throws Exception {
        long projectId = 1L;
        long taskId = 1L;

        doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete("/project/{projectId}/task/{taskId}", projectId, taskId))
                .andExpect(status().isOk())
                .andExpect(content().string("task deleted"));

        verify(taskService).deleteTask(taskId);
    }
}