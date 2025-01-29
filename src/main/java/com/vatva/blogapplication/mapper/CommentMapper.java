package com.vatva.blogapplication.mapper;

import com.vatva.blogapplication.domain.dto.CommentRequestDto;
import com.vatva.blogapplication.domain.dto.CommentResponseDto;
import com.vatva.blogapplication.domain.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentResponseDto toDto(Comment comment);

    Comment toEntity(CommentRequestDto dto);
}