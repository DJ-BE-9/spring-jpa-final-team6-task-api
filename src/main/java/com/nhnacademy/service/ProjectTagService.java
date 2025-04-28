package com.nhnacademy.service;

import com.nhnacademy.model.projectTag.dto.ProjectTagRegisterRequest;
import com.nhnacademy.model.projectTag.dto.RegisterProjectTagRequest;
import com.nhnacademy.model.projectTag.entity.ProjectTag;
import com.nhnacademy.model.tag.dto.ResponseGetTagsDto;

public interface ProjectTagService {
    // 특정 조회
    ProjectTag findByTaskId(Long projectTagId);

    //task id 별 전체 조회
    ResponseGetTagsDto findTagNameByTaskId(Long taskId);

    //등록
    ProjectTag registerProjectTag(ProjectTagRegisterRequest request);

    // 태그 리스트 등록
    void registerProjectTagList(RegisterProjectTagRequest request, long taskId);

    //삭제
    void delete(long projectTagId);

    void deleteByTaskId(long taskId);
}
