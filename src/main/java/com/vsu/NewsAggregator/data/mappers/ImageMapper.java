package com.vsu.NewsAggregator.data.mappers;

import com.vsu.NewsAggregator.data.dto.ImageDto;
import com.vsu.NewsAggregator.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(Image item);

    List<ImageDto> toDto(List<Image> items);
}
