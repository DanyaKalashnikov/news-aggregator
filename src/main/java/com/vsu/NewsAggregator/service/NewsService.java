package com.vsu.NewsAggregator.service;

import com.vsu.NewsAggregator.data.dto.NewsDto;
import com.vsu.NewsAggregator.data.mappers.NewsMapper;
import com.vsu.NewsAggregator.exception.NotFoundException;
import com.vsu.NewsAggregator.model.News;
import com.vsu.NewsAggregator.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;


    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public NewsDto getNews(UUID id) {
        return NewsMapper.INSTANCE.toDto(
                newsRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("News with this id not found"))
        );
    }

    public boolean isExists(String title){
        return newsRepository.existsByTitle(title);
    }

    public List<NewsDto> getAllNews() {
        return NewsMapper.INSTANCE.toDto(newsRepository.findByOrderByDateTime());
    }

    public List<NewsDto> getAllNewsByTag(UUID id){
        return NewsMapper.INSTANCE.toDto(newsRepository.findByOrderByDateTime(id));
    }
}
