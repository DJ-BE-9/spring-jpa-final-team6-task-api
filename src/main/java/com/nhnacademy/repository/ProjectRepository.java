package com.nhnacademy.repository;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.project.type.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectByProjectId(long projectId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Project p SET p.projectState = :projectState WHERE p.projectId = :projectId")
    void stateProject(State projectState, long projectId);

}
