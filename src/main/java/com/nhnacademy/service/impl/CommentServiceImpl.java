package com.nhnacademy.service.impl;

import com.nhnacademy.exception.comment.CommentNotFoundException;
import com.nhnacademy.model.comment.dto.RegisterCommentRequest;
import com.nhnacademy.model.comment.dto.UpdateCommentRequest;
import com.nhnacademy.model.comment.entity.Comment;
import com.nhnacademy.repository.CommentRepository;
import com.nhnacademy.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void registerComment(RegisterCommentRequest request) {
        if(Objects.isNull(request) || Objects.isNull(request.getTaskId()) || Objects.isNull(request.getCommentContent()) || Objects.isNull(request.getCommentWriterId())) {
            throw new IllegalArgumentException();
        }
        Comment comment = new Comment(request.getCommentContent(), request.getTaskId(), request.getCommentWriterId());

        commentRepository.save(comment);
    }

    @Override
    public void updateComment(long commentId, UpdateCommentRequest request) {
        if(Objects.isNull(request) || Objects.isNull(request.getCommentContent())){
            throw new IllegalArgumentException();
        }
        Comment comment = getCommentById(commentId);
        comment.setCommentContent(request.getCommentContent());
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    public Comment getCommentById(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFoundException(commentId)
        );
    }

    @Override
    public List<Comment> getCommentsByTaskId(long taskId) {
        List<Comment> commentList = commentRepository.findAllByTaskId(taskId);
        return commentList;
    }


}
