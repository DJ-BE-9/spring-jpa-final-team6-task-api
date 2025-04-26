package com.nhnacademy.model.project.entity;

import com.nhnacademy.model.project.type.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private long projectId;

    @NotNull
    @Column(name = "project_name", unique = true)
    private String projectName;

    @NotNull
    @Column(name = "project_state")
    @Enumerated(EnumType.STRING)
    private State projectState;

    public Project(String projectName, State projectState) {
        this.projectName = projectName;
        this.projectState = projectState;
    }
}
