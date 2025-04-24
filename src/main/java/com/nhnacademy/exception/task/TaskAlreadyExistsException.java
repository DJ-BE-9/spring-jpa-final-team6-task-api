package com.nhnacademy.exception.task;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException(String taskTitle) {
        super(taskTitle + " already exists");
    }
}
