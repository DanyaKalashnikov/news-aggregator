package com.vsu.NewsAggregator.data.mappers;

import com.vsu.NewsAggregator.data.dto.NewsDto;
import com.vsu.NewsAggregator.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto toDto(News item);

    List<NewsDto> toDto(List<News> items);
}
