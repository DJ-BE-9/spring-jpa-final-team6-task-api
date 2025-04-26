package com.nhnacademy.model.projectMember.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProjectMemberRequest {
    private String userId;
    private boolean projectManager;
}
