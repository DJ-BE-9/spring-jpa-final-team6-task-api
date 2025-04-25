package com.nhnacademy.exception;

public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException(String message) {
        super(message);
    }
}
