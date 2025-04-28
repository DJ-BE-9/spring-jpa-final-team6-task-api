package com.nhnacademy.controller;

import com.nhnacademy.model.projectTag.dto.RegisterProjectTagRequest;
import com.nhnacademy.model.tag.dto.ResponseGetTagsDto;
import com.nhnacademy.service.ProjectTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectTagController {

    private final ProjectTagService projectTagService;

    //등록
    /*@PostMapping("/project/{projectId}/task/{taskId}/projectTag")
    public ResponseEntity<ProjectTag> registerTaskTag(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @RequestBody ProjectTagRegisterRequest request) {
        ProjectTag projectTag = projectTagService.registerProjectTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectTag);
    }*/

    //태그 리스트 등록
    @PostMapping("/project/{projectId}/task/{taskId}/projectTag")
    public ResponseEntity<Void> registerTaskTagList(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @RequestBody RegisterProjectTagRequest request) {
        projectTagService.registerProjectTagList(request, taskId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //전체 조회
    @GetMapping("/project/{projectId}/task/{taskId}/projectTag")
    public ResponseEntity<ResponseGetTagsDto> getProjectTag(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId) {
        ResponseGetTagsDto tagResponses = projectTagService.findTagNameByTaskId(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(tagResponses);
    }

    //삭제
    @DeleteMapping("/project/{projectId}/task/{taskId}/projectTag/{projectTagId}")
    public ResponseEntity<String> deleteTaskTag(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long projectTagId) {
        projectTagService.delete(projectTagId);
        return ResponseEntity.ok("Deleted task tag");
    }

    // task 삭제
    @DeleteMapping("/project/{projectId}/task/{taskId}/projectTag")
    public ResponseEntity<String> deleteProjectTag(@PathVariable Long projectId, @PathVariable Long taskId) {
        projectTagService.deleteByTaskId(taskId);
        return ResponseEntity.ok("Deleted task tags");
    }


}