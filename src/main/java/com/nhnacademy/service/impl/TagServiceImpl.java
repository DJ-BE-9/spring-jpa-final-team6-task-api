package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.tag.TagAlreadyExistsException;
import com.nhnacademy.exception.tag.TagNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.ResponseGetTagDto;
import com.nhnacademy.model.tag.dto.ResponseGetTagsDto;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.repository.TagRepository;
import com.nhnacademy.service.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;


    @Override
    public boolean existsTag(long tagId) {
        return tagRepository.existsById(tagId);
    }

    @Override
    public Tag findByTagId(long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));
    }

    @Override
    public List<ResponseGetTagDto> findAllByProjectId(Project project) {
        List<Tag> tags = tagRepository.findAllByProject(project);
        return tags.stream()
                .map(tag -> new ResponseGetTagDto(tag.getTagId(),tag.getTagName()))
                .collect(Collectors.toList());
    }

    @Override
     public Tag registerTag(TagRegisterRequest request, long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));
        String tagName = request.getTagName();
        if(tagRepository.existsByTagNameAndProject_ProjectId(tagName,projectId)) {
            throw new TagAlreadyExistsException(tagName);
        }
        Tag tag = new Tag(request.getTagName(),project);
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(long tagId, TagUpdateRequest request, long projectId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));
        String tagName = request.getTagName();
        if(tagRepository.existsByTagNameAndProject_ProjectId(tagName,projectId)) {
            throw new TagAlreadyExistsException(tagName);
        }
        tag.setTagName(request.getTagName());
        return tagRepository.save(tag);
    }

    @Override
    public void delete(long tagId) {
        if(!tagRepository.existsById(tagId)) {
            throw new TagNotFoundException(tagId);
        }
        tagRepository.deleteById(tagId);
    }
}
