package com.vsu.NewsAggregator.service;

import com.vsu.NewsAggregator.data.dto.ImageDto;
import com.vsu.NewsAggregator.data.mappers.ImageMapper;
import com.vsu.NewsAggregator.exception.NotFoundException;
import com.vsu.NewsAggregator.model.Image;
import com.vsu.NewsAggregator.repository.ImageRepository;
import com.vsu.NewsAggregator.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageDto saveImage(Image image){
        return ImageMapper.INSTANCE.toDto(imageRepository.save(image));
    }

    public ImageDto getImage(UUID id){
        return ImageMapper.INSTANCE.toDto(
                imageRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("News with this id not found"))
        );
    }

    public List<ImageDto> getAllImagesByNews(UUID newsId){
        return ImageMapper.INSTANCE.toDto(imageRepository.findByOrderByPosition(newsId));
    }
}
