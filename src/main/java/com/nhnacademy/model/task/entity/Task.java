package com.nhnacademy.model.task.entity;


import com.nhnacademy.model.milestone.entity.Milestone;
import com.nhnacademy.model.project.entity.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    public Task(String taskTitle, String taskDescription, Project project, Milestone milestone) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.project = project;
        this.milestone = milestone;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private long taskId;

    @NotNull
    @Setter
    @Column(name = "task_title", unique = true)
    private String taskTitle;

    @NotNull
    @Setter
    @Column(name = "task_description")
    private String taskDescription;

    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name="milestone_id")
    private Milestone milestone;


}
