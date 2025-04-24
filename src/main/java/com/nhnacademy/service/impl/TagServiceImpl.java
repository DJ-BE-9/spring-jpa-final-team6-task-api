package com.nhnacademy.service.impl;

import com.nhnacademy.exception.ProjectNotFoundException;
import com.nhnacademy.exception.tag.TagAlreadyExistsException;
import com.nhnacademy.exception.tag.TagNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.repository.TagRepository;
import com.nhnacademy.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;


    @Override
    public boolean existsTag(long tagId) {
        return tagRepository.existsById(tagId);
    }

    @Override
    public Tag findByIdAndProjectId(long tagId) {
        if(!existsTag(tagId)) {
            throw new TagNotFoundException(tagId);
        }
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));
    }

    @Override
    public List<Tag> findAllByProjectId(Project project) {
        //TODO 예외처리 ?
        return tagRepository.findAllByProject(project);
    }

    @Override
     public Tag save(TagRegisterRequest request, long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId+" not found"));
        String tagName = request.getTagName();
        if(tagRepository.existsByTagName(tagName)) {
            throw new TagAlreadyExistsException(tagName);
        }
        Tag tag = new Tag(null, request.getTagName(),project);
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(long tagId, TagUpdateRequest request) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new TagNotFoundException(tagId));
        tag.setTagName(request.getTagName());
        return tagRepository.save(tag);
    }

    @Override
    public void delete(long tagId) {
        if(!existsTag(tagId)) {
            throw new TagNotFoundException(tagId);
        }
        tagRepository.deleteById(tagId);
    }
}
