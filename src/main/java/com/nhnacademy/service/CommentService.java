package com.nhnacademy.service;

import com.nhnacademy.model.comment.dto.RegisterCommentRequest;
import com.nhnacademy.model.comment.dto.UpdateCommentRequest;
import com.nhnacademy.model.comment.entity.Comment;

import java.util.List;

public interface CommentService {
    void registerComment(RegisterCommentRequest request);
    void updateComment(long commentId ,UpdateCommentRequest request);
    void deleteComment(long commentId);
    Comment getCommentById(long commentId);
    List<Comment> getCommentsByTaskId(long taskId);
}
