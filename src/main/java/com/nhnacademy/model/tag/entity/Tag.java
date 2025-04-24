package com.nhnacademy.model.tag.entity;

import com.nhnacademy.model.project.entity.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @NotNull
    @Setter
    @Column(name = "tag_name", unique = true)
    private String tagName;

    //fk
    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
