package com.nhnacademy.exception;

public class MilestoneNameAlreadyExistsException extends RuntimeException {
    public MilestoneNameAlreadyExistsException(String milestoneName) {
        super(milestoneName + " already exists");
    }
}
