package com.vsu.NewsAggregator.model.old.ycombinator;

import com.vsu.NewsAggregator.model.old.ItemY;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelY {

    private List<ItemY> items;
}
