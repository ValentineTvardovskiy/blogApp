package com.vatva.blogapplication.service;

import com.vatva.blogapplication.domain.dto.CommentRequestDto;
import com.vatva.blogapplication.domain.dto.CommentResponseDto;
import com.vatva.blogapplication.exception.ResourceNotFoundException;
import com.vatva.blogapplication.mapper.CommentMapper;
import com.vatva.blogapplication.repository.ArticleRepository;
import com.vatva.blogapplication.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;
  private final CommentMapper commentMapper;

  public List<CommentResponseDto> getCommentsForArticle(Long articleId, Pageable pageable) {
    return commentRepository.findAllByArticleId(articleId, pageable)
        .stream()
        .map(commentMapper::toDto)
        .collect(Collectors.toList());
  }

  @Transactional
  public CommentResponseDto addCommentToArticle(Long articleId, CommentRequestDto requestDto) {
    var article = articleRepository.findById(articleId)
        .orElseThrow(() -> new ResourceNotFoundException("Article not found with ID: " + articleId));

    var comment = commentMapper.toEntity(requestDto);
    comment.setArticle(article);

    var savedComment = commentRepository.save(comment);
    return commentMapper.toDto(savedComment);
  }

  @Transactional
  public Optional<CommentResponseDto> updateComment(Long commentId, CommentRequestDto requestDto) {
    return commentRepository.findById(commentId).map(comment -> {
      comment.setContent(requestDto.content());
      return commentMapper.toDto(comment);
    });
  }

  @Transactional
  public void deleteComment(Long id) {
    try {
      commentRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Comment not found with ID: " + id);
    }
  }
}
