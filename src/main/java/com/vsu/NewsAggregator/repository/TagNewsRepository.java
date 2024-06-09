package com.vsu.NewsAggregator.repository;

import com.vsu.NewsAggregator.model.TagNews;
import com.vsu.NewsAggregator.model.compositeKey.TagNewsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagNewsRepository extends JpaRepository<TagNews, TagNewsKey> {
}
