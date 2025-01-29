package com.vatva.blogapplication.domain.dto;

import java.time.Instant;

public record ArticleUpdateDto(String title, String content, Instant publishedDate) {}
