package com.nhnacademy.model.task.entity;


import com.nhnacademy.model.projectTag.entity.ProjectTag;
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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;

    @NotNull
    @Column(name = "task_title")
    private String taskTitle;

    @NotNull
    @Column(name = "task_description")
    private String taskDescription;

    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectTag projectTag;


}
