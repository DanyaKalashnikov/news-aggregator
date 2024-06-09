package com.vsu.NewsAggregator.controllers;

import com.vsu.NewsAggregator.data.dto.TagDto;
import com.vsu.NewsAggregator.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<TagDto> getAllTags() {
        return tagService.getAllTags();
    }
}
