package com.nhnacademy.exception.project;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(long projectId) {
        super("Project with id " + projectId + " not found");
    }
}
