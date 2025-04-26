package com.nhnacademy.exception.milestone;

public class MilestoneNameAlreadyExistsException extends RuntimeException {
    public MilestoneNameAlreadyExistsException(String milestoneName) {
        super(milestoneName + " already exists");
    }
}
