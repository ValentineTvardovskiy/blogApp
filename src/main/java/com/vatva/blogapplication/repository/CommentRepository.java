package com.vatva.blogapplication.repository;

import com.vatva.blogapplication.domain.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.article.id = :articleId")
    void directDeleteByArticleId(@Param("articleId") Long articleId);
}