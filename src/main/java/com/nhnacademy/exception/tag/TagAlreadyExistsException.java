package com.nhnacademy.exception.tag;

public class TagAlreadyExistsException extends RuntimeException {
    public TagAlreadyExistsException(String tagName) {
        super("Tag " + tagName + " already exists");
    }
}
