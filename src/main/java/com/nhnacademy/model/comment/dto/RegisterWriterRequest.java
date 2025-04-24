package com.nhnacademy.model.comment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterWriterRequest {

    @NotNull
    String userId;

    @NotNull
    String commentContent;


}
