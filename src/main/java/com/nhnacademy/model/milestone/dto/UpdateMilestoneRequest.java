package com.nhnacademy.model.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMilestoneRequest {
    String milestoneName;
    ZonedDateTime milestoneStartedAt;
    ZonedDateTime milestoneEndedAt;
}
