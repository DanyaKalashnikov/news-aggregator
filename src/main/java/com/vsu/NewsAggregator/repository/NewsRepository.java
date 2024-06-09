package com.vsu.NewsAggregator.repository;

import com.vsu.NewsAggregator.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {

    @Query("SELECT n FROM News n WHERE n.tagsId = :tagId")
    List<News> findByOrderByDateTime(@Param("tagId") UUID tagId);

    List<News> findByOrderByDateTime();

    boolean existsByTitle(String title);
}
