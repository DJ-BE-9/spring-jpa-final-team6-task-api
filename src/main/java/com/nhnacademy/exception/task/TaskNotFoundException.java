package com.nhnacademy.exception.task;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(long taskId) {
        super(taskId + " not found");
    }
}
