package com.nhnacademy.controller;

import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("project/{projectId}/task/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable long projectId, @PathVariable long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    @GetMapping("project/{projectId}/task")
    public ResponseEntity<List<Task>> getTasks(@PathVariable long projectId) {
        List<Task> taskList = taskService.getAllTasksByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @PostMapping("/project/{projectId}/task")
    public ResponseEntity<Task> registerTask(@Valid @RequestBody TaskRegisterRequest taskRegisterRequest, @PathVariable Long projectId) {
        Task task = taskService.save(taskRegisterRequest, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/project/{projectId}/task/{taskId}")
    public ResponseEntity<String> updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest, @PathVariable long projectId, @PathVariable long taskId) {
        taskService.updateTask(taskId, taskUpdateRequest);
        return ResponseEntity.ok("Task updated");
    }

    @DeleteMapping("/project/{projectId}/task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long projectId, @PathVariable long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("task deleted");
    }

}
