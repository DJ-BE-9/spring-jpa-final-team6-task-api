package com.nhnacademy.model.projectTag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTagRegisterRequest {
    private long tagId;
    private long taskId;
}
