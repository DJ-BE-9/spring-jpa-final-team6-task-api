package com.nhnacademy.model.milestone.entity;

import com.nhnacademy.model.project.entity.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter
    private String milestoneName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name="project_id")
    private Project project;

    @Setter
    private ZonedDateTime milestoneStartedAt;
    @Setter
    private ZonedDateTime milestoneEndedAt;

    public Milestone(String milestoneName, ZonedDateTime milestoneStartedAt, ZonedDateTime milestoneEndedAt, Project project) {
        this.milestoneName = milestoneName;
        this.milestoneStartedAt = milestoneStartedAt;
        this.milestoneEndedAt = milestoneEndedAt;
        this.project = project;

    }

}
