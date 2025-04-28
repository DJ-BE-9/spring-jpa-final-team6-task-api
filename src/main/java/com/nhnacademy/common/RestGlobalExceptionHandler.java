package com.nhnacademy.common;

import com.nhnacademy.exception.*;
import com.nhnacademy.exception.comment.CommentNotFoundException;
import com.nhnacademy.exception.milestone.MilestoneNameAlreadyExistsException;
import com.nhnacademy.exception.milestone.MilestoneNotFoundException;
import com.nhnacademy.exception.project.EmptyRequestException;
import com.nhnacademy.exception.project.ProjectAlreadyExistsException;
import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.projectMember.AlreadyExistsMemberException;
import com.nhnacademy.exception.projectTag.ProjectTagAlreadyExistsException;
import com.nhnacademy.exception.projectTag.ProjectTagNotFoundException;
import com.nhnacademy.exception.tag.TagAlreadyExistsException;
import com.nhnacademy.exception.tag.TagNotFoundException;
import com.nhnacademy.exception.task.TaskAlreadyExistsException;
import com.nhnacademy.exception.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    // AlreadyExistsException
    @ExceptionHandler({
            ProjectAlreadyExistsException.class,
            TaskAlreadyExistsException.class,
            TagAlreadyExistsException.class,
            ProjectTagAlreadyExistsException.class,
            MilestoneNameAlreadyExistsException.class,
            EmptyRequestException.class,
            AlreadyExistsMemberException.class
    })
    public ResponseEntity<ExceptionResponse> alreadyExistsException(ProjectAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    // NotFoundException
    @ExceptionHandler({
            ProjectNotFoundException.class,
            TagNotFoundException.class,
            TaskNotFoundException.class,
            ProjectTagNotFoundException.class,
            MilestoneNotFoundException.class,
            CommentNotFoundException.class})
    public ResponseEntity<ExceptionResponse> notFoundException(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.toString(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

}
