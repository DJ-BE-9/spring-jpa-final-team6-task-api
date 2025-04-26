package com.nhnacademy.model.projectMember.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterProjectMemberRequest {
    // TODO requestBody에 UserID 받아오기
    private String userId;
    private boolean projectManager;
}
