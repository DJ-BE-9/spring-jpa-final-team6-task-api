package com.nhnacademy.controller.tag;

import com.nhnacademy.model.tag.dto.TagRegisterRequest;
import com.nhnacademy.service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagRegisterController {
    private final TagService tagService;

    @PostMapping("/project/{projectId}/tag")
    public ResponseEntity<String> registerTag(@Valid @RequestBody TagRegisterRequest tagRegisterRequest, @PathVariable("projectId") Long projectId) {
        tagService.save(tagRegisterRequest,projectId);
        return ResponseEntity.ok("Tag registered successfully");
    }
}
