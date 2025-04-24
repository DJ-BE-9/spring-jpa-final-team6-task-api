package com.nhnacademy.controller.task;

import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskUpdateController {
    private final TaskService taskService;

    @PutMapping("/project/{projectId}/task/{taskId}")
    public ResponseEntity<String> updateTask(@RequestBody TaskUpdateRequest taskUpdateRequest, @PathVariable long projectId, @PathVariable long taskId) {
        taskService.updateTask(taskId, taskUpdateRequest);
        return ResponseEntity.ok("Task updated");
    }
}
