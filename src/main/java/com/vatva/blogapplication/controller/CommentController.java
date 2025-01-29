package com.vatva.blogapplication.controller;

import com.vatva.blogapplication.domain.dto.CommentRequestDto;
import com.vatva.blogapplication.domain.dto.CommentResponseDto;
import com.vatva.blogapplication.service.CommentService;
import com.vatva.blogapplication.utils.Const;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Const.API_PATH + Const.ARTICLE_PATH + "/{articleId}" + Const.COMMENT_PATH)
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<List<CommentResponseDto>> getComments(
      @PathVariable Long articleId,
      @PageableDefault(sort = "created", direction = Sort.Direction.DESC) Pageable pageable) {

    var comments = commentService.getCommentsForArticle(articleId, pageable);
    return ResponseEntity.ok(comments);
  }

  @PostMapping
  public ResponseEntity<CommentResponseDto> addComment(
      @PathVariable Long articleId,
      @Valid @RequestBody CommentRequestDto requestDto) {
    var response = commentService.addCommentToArticle(articleId, requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<CommentResponseDto> updateComment(
      @PathVariable Long commentId,
      @Valid @RequestBody CommentRequestDto requestDto) {
    return commentService.updateComment(commentId, requestDto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
    commentService.deleteComment(commentId);
    return ResponseEntity.noContent().build();
  }
}