package com.vsu.NewsAggregator.model;

import com.vsu.NewsAggregator.model.compositeKey.TagNewsKey;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag_news")
public class TagNews {
    @EmbeddedId
    private TagNewsKey id;

    @ManyToOne
    @MapsId("newsId")
    @JoinColumn(name = "news_id")
    private UUID newsId;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private UUID tagId;
}
