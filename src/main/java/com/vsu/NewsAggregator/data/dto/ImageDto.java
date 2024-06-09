package com.vsu.NewsAggregator.data.dto;

import com.vsu.NewsAggregator.model.News;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private UUID id;
    private String name;
    private int position;
    private News news;
}
