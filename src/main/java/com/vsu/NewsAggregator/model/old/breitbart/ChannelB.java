package com.vsu.NewsAggregator.model.old.breitbart;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelB {

    private List<ItemB> items;
}
