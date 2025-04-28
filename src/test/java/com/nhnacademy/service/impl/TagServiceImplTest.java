//package com.nhnacademy.service.impl;
//
//import com.nhnacademy.exception.project.ProjectNotFoundException;
//import com.nhnacademy.exception.tag.TagAlreadyExistsException;
//import com.nhnacademy.exception.tag.TagNotFoundException;
//import com.nhnacademy.model.project.entity.Project;
//import com.nhnacademy.model.tag.dto.TagRegisterRequest;
//import com.nhnacademy.model.tag.dto.TagUpdateRequest;
//import com.nhnacademy.model.tag.entity.Tag;
//import com.nhnacademy.repository.ProjectRepository;
//import com.nhnacademy.repository.TagRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TagServiceImplTest {
//
//    @Mock
//    private TagRepository tagRepository;
//
//    @Mock
//    private ProjectRepository projectRepository;
//
//    private TagServiceImpl tagService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        tagService = new TagServiceImpl(tagRepository, projectRepository);
//    }
//
//    @Test
//    void existsTag() {
//        long tagId = 1L;
//        when(tagRepository.existsById(tagId)).thenReturn(true);
//
//        boolean result = tagService.existsTag(tagId);
//
//        assertTrue(result);
//    }
//
//    @Test
//    void findByTagId() {
//        long tagId = 1L;
//        Tag tag = new Tag("테스트 태그", new Project());
//
//        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
//
//        Tag result = tagService.findByTagId(tagId);
//
//        assertEquals(tag, result);
//    }
//
//    @Test
//    void findByTagId_NotFound() {
//        long tagId = 1L;
//        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
//
//        assertThrows(TagNotFoundException.class, () -> tagService.findByTagId(tagId));
//    }
//
//    @Test
//    void findAllByProjectId() {
//        Project project = new Project();
//        List<Tag> tags = List.of(new Tag("태그1", project));
//
//        when(tagRepository.findAllByProject(project)).thenReturn(tags);
//
//        List<Tag> result = tagService.findAllByProjectId(project);
//
//        assertEquals(tags, result);
//    }
//
//    @Test
//    void registerTag() {
//        long projectId = 1L;
//        TagRegisterRequest request = new TagRegisterRequest("새 태그");
//        Project project = new Project();
//        Tag tag = new Tag("새 태그", project);
//
//        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
//        when(tagRepository.existsByTagNameAndProject_ProjectId(request.getTagName(), projectId)).thenReturn(false);
//        when(tagRepository.save(any(Tag.class))).thenReturn(tag);
//
//        Tag result = tagService.registerTag(request, projectId);
//
//        assertEquals(tag, result);
//    }
//
//    @Test
//    void registerTag_TagNameAlreadyExists() {
//        long projectId = 1L;
//        TagRegisterRequest request = new TagRegisterRequest("이미 있는 태그");
//        Project project = new Project();
//
//        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
//        when(tagRepository.existsByTagNameAndProject_ProjectId(request.getTagName(), projectId)).thenReturn(true);
//
//        assertThrows(TagAlreadyExistsException.class, () -> tagService.registerTag(request, projectId));
//    }
//
//    @Test
//    void updateTag() {
//        long tagId = 1L;
//        TagUpdateRequest request = new TagUpdateRequest("수정된 태그");
//        Tag tag = new Tag("원래 태그", new Project());
//
//        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
//        when(tagRepository.save(tag)).thenReturn(tag);
//
//        Tag result = tagService.updateTag(tagId, request);
//
//        assertEquals("수정된 태그", result.getTagName());
//    }
//
//    @Test
//    void delete() {
//        long tagId = 1L;
//        when(tagRepository.existsById(tagId)).thenReturn(true);
//
//        tagService.delete(tagId);
//
//        verify(tagRepository).deleteById(tagId);
//    }
//
//    @Test
//    void delete_TagNotFound() {
//        long tagId = 1L;
//        when(tagRepository.existsById(tagId)).thenReturn(false);
//
//        assertThrows(TagNotFoundException.class, () -> tagService.delete(tagId));
//    }
//}
/*
package com.nhnacademy.service.impl;

import com.nhnacademy.exception.project.ProjectNotFoundException;
import com.nhnacademy.exception.tag.TagAlreadyExistsException;
import com.nhnacademy.exception.tag.TagNotFoundException;
import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private ProjectRepository projectRepository;

    private TagServiceImpl tagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tagService = new TagServiceImpl(tagRepository, projectRepository);
    }

    @Test
    void existsTag() {
        long tagId = 1L;
        when(tagRepository.existsById(tagId)).thenReturn(true);

        boolean result = tagService.existsTag(tagId);

        assertTrue(result);
    }

    @Test
    void findByTagId() {
        long tagId = 1L;
        Tag tag = new Tag("테스트 태그", new Project());

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        Tag result = tagService.findByTagId(tagId);

        assertEquals(tag, result);
    }

    @Test
    void findByTagId_NotFound() {
        long tagId = 1L;
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        assertThrows(TagNotFoundException.class, () -> tagService.findByTagId(tagId));
    }

    @Test
    void findAllByProjectId() {
        Project project = new Project();
        List<Tag> tags = List.of(new Tag("태그1", project));

        when(tagRepository.findAllByProject(project)).thenReturn(tags);

        List<Tag> result = tagService.findAllByProjectId(project);

        assertEquals(tags, result);
    }

    @Test
    void registerTag() {
        long projectId = 1L;
        TagRegisterRequest request = new TagRegisterRequest("새 태그");
        Project project = new Project();
        Tag tag = new Tag("새 태그", project);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(tagRepository.existsByTagNameAndProject_ProjectId(request.getTagName(), projectId)).thenReturn(false);
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        Tag result = tagService.registerTag(request, projectId);

        assertEquals(tag, result);
    }

    @Test
    void registerTag_TagNameAlreadyExists() {
        long projectId = 1L;
        TagRegisterRequest request = new TagRegisterRequest("이미 있는 태그");
        Project project = new Project();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(tagRepository.existsByTagNameAndProject_ProjectId(request.getTagName(), projectId)).thenReturn(true);

        assertThrows(TagAlreadyExistsException.class, () -> tagService.registerTag(request, projectId));
    }

    @Test
    void updateTag() {
        long tagId = 1L;
        TagUpdateRequest request = new TagUpdateRequest("수정된 태그");
        Tag tag = new Tag("원래 태그", new Project());

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(tagRepository.save(tag)).thenReturn(tag);

        Tag result = tagService.updateTag(tagId, request);

        assertEquals("수정된 태그", result.getTagName());
    }

    @Test
    void delete() {
        long tagId = 1L;
        when(tagRepository.existsById(tagId)).thenReturn(true);

        tagService.delete(tagId);

        verify(tagRepository).deleteById(tagId);
    }

    @Test
    void delete_TagNotFound() {
        long tagId = 1L;
        when(tagRepository.existsById(tagId)).thenReturn(false);

        assertThrows(TagNotFoundException.class, () -> tagService.delete(tagId));
    }
}*/
