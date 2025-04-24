package com.nhnacademy.model.milestone.dto;

import com.nhnacademy.model.project.entity.Project;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMilestoneRequest {
    @NotNull
    String milestoneName;
    @NotNull
    Project project;
    @NotNull
    ZonedDateTime milestoneStartedAt;
    @NotNull
    ZonedDateTime milestoneEndedAt;
}
