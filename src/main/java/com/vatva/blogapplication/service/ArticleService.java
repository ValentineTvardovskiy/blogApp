package com.vatva.blogapplication.service;

import com.vatva.blogapplication.domain.dto.ArticleCreateDto;
import com.vatva.blogapplication.domain.dto.ArticleResponseDto;
import com.vatva.blogapplication.domain.dto.ArticleUpdateDto;
import com.vatva.blogapplication.exception.ResourceNotFoundException;
import com.vatva.blogapplication.mapper.ArticleMapper;
import com.vatva.blogapplication.repository.ArticleRepository;
import com.vatva.blogapplication.domain.projection.ArticleSummery;
import com.vatva.blogapplication.repository.CommentRepository;
import com.vatva.blogapplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final ArticleMapper articleMapper;

  public List<ArticleSummery> getAllArticleSummaries(Pageable pageable) {
    return articleRepository.findAllBy(pageable).getContent();
  }

  @Transactional
  public ArticleResponseDto createArticle(ArticleCreateDto articleDto) {
    var author = userRepository.findById(articleDto.authorId())
        .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + articleDto.authorId()));

    var newArticle = articleMapper.toEntity(articleDto);
    newArticle.setAuthor(author);

    var savedArticle = articleRepository.save(newArticle);
    log.info("Article created successfully with ID: {}, Author ID: {}", savedArticle.getId(), author.getId());
    return articleMapper.toDto(savedArticle);
  }

  public Optional<ArticleResponseDto> findById(Long id) {
    return articleRepository.findByIdWithAuthor(id).map(articleMapper::toDto);
  }

  @Transactional
  public Optional<ArticleResponseDto> updateArticle(Long id, ArticleUpdateDto articleDto) {
    return articleRepository.findById(id).map(article -> {
      articleMapper.updateEntityFromDto(articleDto, article);
      return articleMapper.toDto(article);
    });
  }

  @Transactional
  public void deleteById(Long id) {
    commentRepository.directDeleteByArticleId(id);
    int deleted = articleRepository.directDeleteById(id);

    if (deleted == 0) {
      throw new ResourceNotFoundException("Article not found with ID: " + id);
    }
    log.info("Article and its comments deleted successfully with ID: {}", id);
  }
}
