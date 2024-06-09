package com.vsu.NewsAggregator.data.mappers;

import com.vsu.NewsAggregator.data.dto.TagDto;
import com.vsu.NewsAggregator.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    TagDto toDto(Tag item);

    List<TagDto> toDto(List<Tag> items);
}
