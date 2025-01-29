package com.vatva.blogapplication.domain.dto;

import java.time.Instant;

public record ArticleResponseDto(Long id, String title, String content, Instant publishedDate, UserResponseDto author) {}
