package com.vsu.NewsAggregator.repository;

import com.vsu.NewsAggregator.model.Image;
import com.vsu.NewsAggregator.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query("SELECT t FROM Tag t WHERE t.newsId = :newsId")
    List<Tag> findByOrderByName(@Param("newsId") UUID newsId);

    List<Tag> findByOrderByName();

    @Query("SELECT t FROM Tag t WHERE t.name = :name")
    Tag findByName(@Param("name") String name);

    boolean existsByName(String name);
}
