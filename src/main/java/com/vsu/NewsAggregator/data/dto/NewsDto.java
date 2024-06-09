package com.vsu.NewsAggregator.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vsu.NewsAggregator.model.compositeKey.TagNewsKey;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private UUID id;
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private Set<TagNewsKey> tags;
}
