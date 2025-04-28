package com.nhnacademy.model.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommentRequest {
    String commentContent;
    long taskId;
    String commentWriterId;
}
