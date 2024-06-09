package com.vsu.NewsAggregator.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "id.tagId",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<TagNews> newsId;
}
