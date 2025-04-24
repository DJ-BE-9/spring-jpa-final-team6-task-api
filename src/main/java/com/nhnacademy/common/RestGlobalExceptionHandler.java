package com.nhnacademy.common;

import com.nhnacademy.exception.ProjectAlreadyExistsException;
import com.nhnacademy.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler({ProjectAlreadyExistsException.class})
    public ResponseEntity<String> alreadyExistsException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({ProjectNotFoundException.class})
    public ResponseEntity<String> notFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
