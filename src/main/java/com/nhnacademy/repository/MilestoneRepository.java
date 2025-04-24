package com.nhnacademy.repository;

import com.nhnacademy.model.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    // 프로젝트가 생성한 Milestone 찾기 (프로젝트ID 기준)
    List<Milestone> findAllByProject_ProjectId(long projectId);
    // 프로젝트가 생성한 Milestone 찾기 (프로젝트명 기준) 
    List<Milestone> findAllByProject_ProjectName(String name);
    void deleteAllByProject_ProjectId(long projectId);
    boolean existsMilestoneByMilestoneNameAndProject_ProjectId(String milestoneName, long projectId);
}
