package com.nhnacademy.controller.task;

import com.nhnacademy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskDeleteController {
    private final TaskService taskService;

    @DeleteMapping("/project/{projectId}/task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long projectId, @PathVariable long taskId) {
        taskService.deleteTask(taskId);
    }
}
