package com.nhnacademy.model.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRegisterRequest {
    private String taskTitle;
    private String taskDescription;

    //TODO milestone ?
}
