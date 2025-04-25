package com.nhnacademy.exception.projectTag;

public class ProjectTagNotFoundException extends RuntimeException {
    public ProjectTagNotFoundException(long projectTagId) {
        super(projectTagId + " not found");
    }
}
