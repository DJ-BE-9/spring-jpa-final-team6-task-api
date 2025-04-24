package com.nhnacademy.model.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterCommentRequest {

    @NotNull
    String commentContent;

    @NotNull
    long taskId;
}
