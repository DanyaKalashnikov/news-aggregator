package com.vsu.NewsAggregator.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private int position;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;
}
