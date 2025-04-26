package com.nhnacademy.service;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;

public interface ProjectMemberService {

    Project registerMemberByProject(long projectId, RegisterProjectMemberRequest request);
    void deleteProject(long projectId);
}
