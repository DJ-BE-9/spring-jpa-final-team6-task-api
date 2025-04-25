package com.nhnacademy.model.projectTag.entity;

import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.model.task.entity.Task;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectTag {

    public ProjectTag(Tag tag, Task task) {
        this.tag = tag;
        this.task = task;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long task_tag_id;


    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}
