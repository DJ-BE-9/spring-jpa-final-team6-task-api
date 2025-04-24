package com.nhnacademy.model.projectMember.entity;

import com.nhnacademy.model.project.entity.Project;
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
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_member_id")
    private long projectMemberId;

    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean projectManager;

    //TODO user fk
    @NotNull
    @Column(name = "user_id")
    private String userId;

    public ProjectMember(Project project, String userId, boolean projectManager) {
        this.project = project;
        this.userId = userId;
        this.projectManager = projectManager;
    }


}
