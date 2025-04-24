package com.nhnacademy.model.milestone.entity;

import com.nhnacademy.model.project.entity.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long milestoneId;

    @NotNull
    private String milestoneName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="project_id")
    private Project project;

    private ZonedDateTime milestoneStartedAt;
    private ZonedDateTime milestoneEndedAt;

}
