package com.vatva.blogapplication.domain.dto;

import java.time.Instant;

public record CommentResponseDto(Long id, Instant created, String content) {}