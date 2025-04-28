package com.nhnacademy.controller;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.*;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.service.ProjectService;
import com.nhnacademy.service.TagService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final ProjectService projectService;

    //특정 조회
    @GetMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<ResponseGetTagDto> getTag(@PathVariable long projectId, @PathVariable long tagId) {
        Tag tag = tagService.findByTagId(tagId);
        ResponseGetTagDto responseGetTagDto = new ResponseGetTagDto(tag.getTagId(), tag.getTagName());
//        ResponseGetDto responseGetDto = new ResponseGetDto(tag.getTagId(),tag.getTagName()); //TODO 보류
        return ResponseEntity.status(HttpStatus.OK).body(responseGetTagDto);
    }

    //프로젝트 별 조회
    @GetMapping("/project/{projectId}/tag")
    public ResponseEntity<ResponseGetTagsDto> getTags(@PathVariable long projectId) {
        Project project = projectService.getProjectById(projectId);

        List<ResponseGetTagDto> tagList = tagService.findAllByProjectId(project);
        ResponseGetTagsDto responseGetTagsDto = new ResponseGetTagsDto(tagList);
//        ResponseGetAllDto responseGetAllDto = new ResponseGetAllDto(tagList, "Tag List retrieved successfully"); //TODO 보류
        return ResponseEntity.status(HttpStatus.OK).body(responseGetTagsDto);
    }

    //테그 등록
    @PostMapping("/project/{projectId}/tag")
    @Transactional
    public ResponseEntity<ResponsePostTagDto> registerTag(@Valid @RequestBody TagRegisterRequest tagRegisterRequest, @PathVariable("projectId") Long projectId) {
        Tag tag = tagService.registerTag(tagRegisterRequest, projectId);
        ResponsePostTagDto responsePostTagDto = new ResponsePostTagDto(tag.getTagName());
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePostTagDto);
    }

    @PutMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> updateTag(@RequestBody TagUpdateRequest tagUpdateRequest, @PathVariable long projectId, @PathVariable long tagId) {
        tagService.updateTag(tagId, tagUpdateRequest,projectId);

        return ResponseEntity.ok().body(tagId + " updated");
    }

    @DeleteMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable long projectId, @PathVariable long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.ok("Tag " + tagId + " deleted successfully");
    }
}
