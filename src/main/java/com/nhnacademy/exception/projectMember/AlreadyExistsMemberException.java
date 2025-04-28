package com.nhnacademy.exception.projectMember;

public class AlreadyExistsMemberException extends RuntimeException {
  public AlreadyExistsMemberException(String message) {
    super(message);
  }
}
