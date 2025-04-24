package com.nhnacademy.service;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.projectMember.dto.RegisterProjectMemberRequest;

public interface ProjectMemberService {

    Project registerMemberByProject(RegisterProjectMemberRequest request, String userId);
    void deleteProject(long projectId);
}
