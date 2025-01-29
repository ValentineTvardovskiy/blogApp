package com.vatva.blogapplication.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;

public record ArticleCreateDto(
    @NotBlank(message = "Title must not be blank") String title,
    @NotBlank(message = "Content must not be blank") String content,
    @NotNull @Positive (message = "Article should have valid author") Long authorId,
    Instant publishedDate) {}
