package com.nhnacademy.model.tag.dto;

import com.nhnacademy.model.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetAllDto {
    private List<Tag> tagList;
    private String message;
}
