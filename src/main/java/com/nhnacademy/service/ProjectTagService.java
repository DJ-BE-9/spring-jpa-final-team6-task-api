package com.nhnacademy.service;

import com.nhnacademy.model.projectTag.dto.ProjectTagByTagNameResponse;
import com.nhnacademy.model.projectTag.dto.ProjectTagRegisterRequest;
import com.nhnacademy.model.projectTag.dto.RegisterProjectTagRequest;
import com.nhnacademy.model.projectTag.entity.ProjectTag;

import java.util.List;

public interface ProjectTagService {
    // 특정 조회
    ProjectTag findByTaskId(Long projectTagId);

    //task id 별 전체 조회
    List<ProjectTagByTagNameResponse> findTagNameByTaskId(Long taskId);

    //등록
    ProjectTag registerProjectTag(ProjectTagRegisterRequest request);

    // 태그 리스트 등록
    void registerProjectTagList(RegisterProjectTagRequest request, long taskId);

    //삭제
    void delete(long projectTagId);
}
