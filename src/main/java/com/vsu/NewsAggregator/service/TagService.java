package com.vsu.NewsAggregator.service;

import com.vsu.NewsAggregator.data.dto.TagDto;
import com.vsu.NewsAggregator.data.mappers.TagMapper;
import com.vsu.NewsAggregator.exception.NotFoundException;
import com.vsu.NewsAggregator.model.Tag;
import com.vsu.NewsAggregator.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public TagDto saveTag(Tag tag) {
        return TagMapper.INSTANCE.toDto(tagRepository.save(tag));
    }

    public TagDto getTag(UUID id) {
        return TagMapper.INSTANCE.toDto(tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag with this id not found")));
    }

    public UUID getTagByName(String name) {
        if (tagRepository.existsByName(name)) {
            return tagRepository.findByName(name).getId();
        }
        return saveTag(Tag.builder()
                .name(name)
                .build()).getId();
    }

    public List<TagDto> getAllTags() {
        return TagMapper.INSTANCE.toDto(tagRepository.findByOrderByName());
    }

    public List<TagDto> getAllTagsByNews(UUID newsId) {
        return TagMapper.INSTANCE.toDto(tagRepository.findByOrderByName(newsId));
    }
}
