package com.nhnacademy.controller.tag;


import com.nhnacademy.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagDeleteController {
    private final TagService tagService;

    @DeleteMapping("/project/{projectId}/tag/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable long projectId, @PathVariable long tagId) {
        tagService.delete(tagId);
        return ResponseEntity.ok("Tag " + tagId + " deleted successfully");
    }
}
