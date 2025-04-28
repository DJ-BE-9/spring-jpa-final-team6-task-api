package com.nhnacademy.exception.project;

public class EmptyRequestException extends RuntimeException {
    public EmptyRequestException(String message) {
        super(message);
    }
}
