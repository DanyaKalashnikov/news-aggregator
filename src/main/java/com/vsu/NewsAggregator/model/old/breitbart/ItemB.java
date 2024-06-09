package com.vsu.NewsAggregator.model.old.breitbart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemB {

    private String title;

    private String link;

    private List<String> text;

    private List<String> tags;
}
