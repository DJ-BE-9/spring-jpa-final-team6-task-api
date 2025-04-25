package com.nhnacademy.controller;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.service.ProjectService;
import com.nhnacademy.service.TagService;
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
    public ResponseEntity<Tag> getTag(@PathVariable long projectId, @PathVariable long tagId) {
        Tag tag = tagService.findByTagId(tagId);
//        ResponseGetDto responseGetDto = new ResponseGetDto(tag.getTagId(),tag.getTagName()); //TODO 보류
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    //프로젝트 별 조회
    @GetMapping("/project/{projectId}/tag")
    public ResponseEntity<List<Tag>> getTags(@PathVariable long projectId) {
        Project project = projectService.getProjectById(projectId);

        List<Tag> tagList = tagService.findAllByProjectId(project);

//        ResponseGetAllDto responseGetAllDto = new ResponseGetAllDto(tagList, "Tag List retrieved successfully"); //TODO 보류
        return ResponseEntity.status(HttpStatus.OK).body(tagList);
    }

    @PostMapping("/project/{projectId}/tag")
    public ResponseEntity<Tag> registerTag(@Valid @RequestBody TagRegisterRequest tagRegisterRequest, @PathVariable("projectId") Long projectId) {
        Tag tag = tagService.save(tagRegisterRequest,projectId);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @PutMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> updateTag(@RequestBody TagUpdateRequest tagUpdateRequest, @PathVariable long projectId, @PathVariable long tagId) {
        tagService.updateTag(tagId, tagUpdateRequest);

        return ResponseEntity.ok().body(tagId + " updated");
    }

    @DeleteMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable long projectId, @PathVariable long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.ok("Tag " + tagId + " deleted successfully");
    }
}
