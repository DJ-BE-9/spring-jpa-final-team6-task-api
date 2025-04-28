package com.nhnacademy.repository;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository  extends JpaRepository<Tag, Long> {
    List<Tag> findAllByProject(Project project);
    boolean existsByTagNameAndProject_ProjectId(String tagName, long projectId);
}
