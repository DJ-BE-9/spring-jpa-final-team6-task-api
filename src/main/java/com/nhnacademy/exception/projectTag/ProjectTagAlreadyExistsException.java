package com.nhnacademy.exception.projectTag;

public class ProjectTagAlreadyExistsException extends RuntimeException {
    public ProjectTagAlreadyExistsException() {
        super("This tag already exists");
    }
}
