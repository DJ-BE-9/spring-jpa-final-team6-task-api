package com.nhnacademy.exception.comment;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(long commentId) {
        super(commentId + " comment not found");
    }
}
