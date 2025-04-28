package com.nhnacademy.service;

import com.nhnacademy.model.project.dto.ProjectMemberRequest;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;

import java.util.List;

public interface ProjectMemberService {

    Project registerMemberByProject(long projectId, RegisterProjectMemberRequest request);
    void deleteProject(long projectId);

    List<ProjectMemberRequest> getProjectMembers(long projectId);

    void deleteProjectMemberByUserId(String memberId, long projectId);

    void registerMemberProject(long projectId, RegisterProjectMemberRequest request);
}
