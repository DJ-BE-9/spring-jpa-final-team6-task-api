package com.nhnacademy.service;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.ResponseGetTagDto;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;

import java.util.List;

public interface TagService {

    boolean existsTag(long tagId);

    //특정 조회
    Tag findByTagId(long tagId);

    //프로젝트 별 조회
    List<ResponseGetTagDto> findAllByProjectId(Project project);

    //등록
    Tag registerTag(TagRegisterRequest request, long projectId);

    //수정
    Tag updateTag(long tagId, TagUpdateRequest request,long projectId);

    //삭제
    void delete(long tagId);

}
