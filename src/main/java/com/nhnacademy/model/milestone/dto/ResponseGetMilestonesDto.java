package com.nhnacademy.model.milestone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetMilestonesDto {
    List<ResponseGetMilestoneDto> milestones = new ArrayList<>();
}
