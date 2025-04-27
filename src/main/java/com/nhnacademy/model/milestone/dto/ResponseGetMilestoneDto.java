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
public class ResponseGetMilestoneDto {
    private Long milestoneId;
    private String milestoneName;
    private ZonedDateTime milestoneStartedAt;
    private ZonedDateTime milestoneEndedAt;
}
