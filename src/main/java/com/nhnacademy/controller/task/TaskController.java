package com.nhnacademy.controller.task;

import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("project/{projectId}/task/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable long projectId, @PathVariable long taskId) {
        Task task = taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }


}
