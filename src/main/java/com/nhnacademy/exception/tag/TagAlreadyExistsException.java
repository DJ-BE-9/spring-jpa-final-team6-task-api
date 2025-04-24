package com.nhnacademy.exception.tag;

public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(long tagId) {
        super("Tag " + tagId + " already exists");
    }
}
