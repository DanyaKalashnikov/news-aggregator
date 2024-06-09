package com.vsu.NewsAggregator.controllers;

import com.vsu.NewsAggregator.data.dto.NewsDto;
import com.vsu.NewsAggregator.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping()
    public List<NewsDto> getAllNews(){
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public NewsDto getNews(@PathVariable UUID id){
        return newsService.getNews(id);
    }
}
