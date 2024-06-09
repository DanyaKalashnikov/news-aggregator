package com.vsu.NewsAggregator.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String content;
    private LocalDateTime dateTime;

    @OneToMany(mappedBy = "id.newsId",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<TagNews> tagsId;
}
