package com.nhnacademy.controller.task;


import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskRegisterController {
    private final TaskService taskService;

    @PostMapping("/project/{projectId}/task")
    public ResponseEntity<Task> registerTask(@Valid @RequestBody TaskRegisterRequest taskRegisterRequest, @PathVariable Long projectId) {
        Task task = taskService.save(taskRegisterRequest, projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }
}
