package com.nhnacademy.model.task.entity;


import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectTag.entity.ProjectTag;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    public Task(String taskTitle, String taskDescription, Project project) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.project = project;
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


}
