package com.vsu.NewsAggregator.data.dto;

import com.vsu.NewsAggregator.model.TagNews;
import com.vsu.NewsAggregator.model.compositeKey.TagNewsKey;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private UUID id;
    private String name;
    private Set<TagNewsKey> news;
}
