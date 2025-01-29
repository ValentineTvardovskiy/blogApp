package com.vatva.blogapplication.repository;

import com.vatva.blogapplication.domain.entity.Article;
import com.vatva.blogapplication.domain.projection.ArticleSummery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

  // maybe i didn't get the requirement correctly:
  // write a Spring Data JPA repository query to find all articles published before a certain date.
  // so i just added the query in the repository
  // also, index creation for publishedDate field is required to speed up filter queries like this
  Page<Article> findAllByPublishedDateBefore(Instant publishedDate, Pageable pageable);

  @Query("select a.id as id, a.title as title, a.publishedDate as publishedDate, a.author.name as author from Article a")
  Page<ArticleSummery> findAllBy(Pageable pageable);

  @Query("SELECT a FROM Article a LEFT JOIN FETCH a.author WHERE a.id = :id")
  Optional<Article> findByIdWithAuthor(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query("DELETE FROM Article a WHERE a.id = :id")
  int directDeleteById(@Param("id") Long id);
}