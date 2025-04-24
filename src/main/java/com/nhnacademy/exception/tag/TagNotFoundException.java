package com.nhnacademy.exception.tag;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(long tagId) {
        super("Tag not found: " + tagId);
    }
}
