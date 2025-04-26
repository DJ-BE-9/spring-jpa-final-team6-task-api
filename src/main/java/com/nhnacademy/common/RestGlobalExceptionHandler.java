package com.nhnacademy.common;

import com.nhnacademy.exception.ExceptionResponse;
import com.nhnacademy.exception.MilestoneNotFoundException;
import com.nhnacademy.exception.ProjectAlreadyExistsException;
import com.nhnacademy.exception.ProjectNotFoundException;
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

    //project
    @ExceptionHandler({ProjectAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> alreadyExistsException(ProjectAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler({ProjectNotFoundException.class})
    public ResponseEntity<ExceptionResponse> notFoundException(ProjectNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.toString(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    //tag
    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> tagAlreadyExistsException(TagAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<ExceptionResponse> tagNotFoundException(TagNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    //task
    @ExceptionHandler(TaskAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> taskAlreadyExistsException(TaskAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ExceptionResponse> taskNotFoundException(TaskNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    //projectTag
    @ExceptionHandler(ProjectTagAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> projectAlreadyExistsException(ProjectTagAlreadyExistsException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(ProjectTagNotFoundException.class)
    public ResponseEntity<ExceptionResponse> projectNotFoundException(ProjectTagNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    //milestone
    @ExceptionHandler(MilestoneNotFoundException.class)
    public ResponseEntity<ExceptionResponse> milestoneNotFoundException(MilestoneNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> exception(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

}
