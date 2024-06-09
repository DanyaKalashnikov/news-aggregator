package com.vsu.NewsAggregator.model.compositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TagNewsKey implements Serializable {
    @Column(name = "news_id")
    private UUID newsId;
    @Column(name = "tag_id")
    private UUID tagId;
}
