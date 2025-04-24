package com.nhnacademy.controller.tag;

import com.nhnacademy.model.tag.dto.TagUpdateRequest;
import com.nhnacademy.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TagUpdateController {

    private final TagService tagService;

    @PutMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> updateTag(@RequestBody TagUpdateRequest tagUpdateRequest, @PathVariable long projectId, @PathVariable long tagId) {
        tagService.updateTag(tagId, tagUpdateRequest);

        return ResponseEntity.ok().body(tagId + " updated");
    }
}
