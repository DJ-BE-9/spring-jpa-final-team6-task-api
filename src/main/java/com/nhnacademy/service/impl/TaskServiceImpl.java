package com.nhnacademy.service.impl;

import com.nhnacademy.exception.ProjectNotFoundException;
import com.nhnacademy.exception.task.TaskAlreadyExistsException;
import com.nhnacademy.exception.task.TaskNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.model.task.entity.Task;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.repository.TaskRepository;
import com.nhnacademy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public boolean existsTag(long tagId) {
        return taskRepository.existsById(tagId);
    }

    @Override
    public Task getTaskById(long id) {
        if(!existsTag(id)) {
            throw new TaskNotFoundException(id);
        }
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getAllTasksByProjectId(long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId+ " not found"));
        return taskRepository.findAllByProject(project);
    }

    @Override
    public Task save(TaskRegisterRequest request,long projectId) {
        String title = request.getTaskTitle();
        if(taskRepository.existsByTaskTitle(title)){
            throw new TaskAlreadyExistsException(title);
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId+ " not found"));

        Task task = new Task(request.getTaskTitle(), request.getTaskDescription(), project);
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(long taskId, TaskUpdateRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setTaskTitle(request.getTaskTitle());
        task.setTaskDescription(request.getTaskDescription());
        taskRepository.save(task);
        return task;
    }

    @Override
    public void deleteTask(long taskId) {
//        if(taskRepository.existsById(taskId)) {
//            throw new TaskNotFoundException(taskId);
//        }
        taskRepository.deleteById(taskId);
    }
}
