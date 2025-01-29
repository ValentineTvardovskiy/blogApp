package com.vatva.blogapplication.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDto(
    @NotBlank(message = "Content must not be blank") String content) {}