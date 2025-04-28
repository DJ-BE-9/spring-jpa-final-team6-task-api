package com.nhnacademy.model.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTaskDto {
    private long taskId;
    private String taskTitle;
    private String taskDescription;
    private long milestoneId;
}
