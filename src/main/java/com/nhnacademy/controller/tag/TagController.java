package com.nhnacademy.controller.tag;

import com.nhnacademy.model.project.entity.Project;
import com.nhnacademy.model.tag.dto.ResponseGetAllDto;
import com.nhnacademy.model.tag.dto.ResponseGetDto;
import com.nhnacademy.model.tag.entity.Tag;
import com.nhnacademy.repository.ProjectRepository;
import com.nhnacademy.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final ProjectRepository projectRepository;

    //특정 조회
    @GetMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<ResponseGetDto> getTag(@PathVariable long projectId, @PathVariable long tagId) {
        Tag tag = tagService.findByIdAndProjectId(tagId);

        ResponseGetDto responseGetDto = new ResponseGetDto(tag.getTagId(),tag.getTagName());
        return ResponseEntity.ok(responseGetDto); // TODO
    }

    //프로젝트 별 조회
    @GetMapping("/project/{projectId}/tag")
    public ResponseEntity<ResponseGetAllDto> getTags(@PathVariable long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        List<Tag> tagList = tagService.findAllByProjectId(project);

        ResponseGetAllDto responseGetAllDto = new ResponseGetAllDto(tagList, "Tag List retrieved successfully");
        return ResponseEntity.ok(responseGetAllDto);
    }
}
