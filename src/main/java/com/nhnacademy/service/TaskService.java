package com.nhnacademy.service;


import com.nhnacademy.model.task.dto.TaskRegisterRequest;
import com.nhnacademy.model.task.dto.TaskUpdateRequest;
import com.nhnacademy.model.task.entity.Task;

import java.util.List;

public interface TaskService {
    boolean existsTag(long tagId);
    //get
    //특정 조회
    Task getTaskById(long id);
    //전체 조회
    List<Task> getAllTasksByProjectId(long projectId);
    //register
    Task save(TaskRegisterRequest taskRegisterRequest, long projectId);
    //update
    Task updateTask(long taskId, TaskUpdateRequest request);
    //delete
    void deleteTask(long taskId);
}
