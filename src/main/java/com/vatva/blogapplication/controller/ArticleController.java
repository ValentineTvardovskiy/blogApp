package com.vatva.blogapplication.controller;

import com.vatva.blogapplication.domain.dto.ArticleCreateDto;
import com.vatva.blogapplication.domain.dto.ArticleResponseDto;
import com.vatva.blogapplication.domain.dto.ArticleUpdateDto;
import com.vatva.blogapplication.domain.projection.ArticleSummery;
import com.vatva.blogapplication.service.ArticleService;
import com.vatva.blogapplication.utils.Const;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping(Const.API_PATH + Const.ARTICLE_PATH)
@AllArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping
  public ResponseEntity<List<ArticleSummery>> getAllArticles(
      @PageableDefault(sort = "publishedDate", direction = Sort.Direction.DESC) Pageable pageable) {

    var articleSummaries = articleService.getAllArticleSummaries(pageable);
    return ResponseEntity.ok(articleSummaries);
  }

  @PostMapping
  public ResponseEntity<ArticleResponseDto> createArticle(@Valid @RequestBody ArticleCreateDto request) {
    var response = articleService.createArticle(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long id) {
    return articleService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ArticleResponseDto> updateArticle(@PathVariable Long id,
                                                          @RequestBody ArticleUpdateDto request) {
    return articleService.updateArticle(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
    articleService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}