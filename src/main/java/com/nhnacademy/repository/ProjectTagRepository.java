package com.nhnacademy.repository;

import com.nhnacademy.model.projectTag.entity.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {
    List<ProjectTag> findAllByTask_TaskId(Long taskId);
    boolean existsByTag_TagIdAndTask_TaskId(Long tagId, Long taskId);
}
