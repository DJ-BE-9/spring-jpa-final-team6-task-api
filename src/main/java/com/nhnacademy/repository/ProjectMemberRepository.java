package com.nhnacademy.repository;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    @Query("select pm.project from ProjectMember pm where pm.userId = :userId")
    List<Project> findAllProjectByUserId(String userId);

    void deleteByProject_ProjectId(long projectProjectId);
}
