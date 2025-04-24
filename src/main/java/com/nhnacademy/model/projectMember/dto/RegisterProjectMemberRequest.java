package com.nhnacademy.model.projectMember.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProjectMemberRequest {

    private long projectId;
    private boolean projectManager;
}
