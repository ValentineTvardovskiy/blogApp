package com.vatva.blogapplication.mapper;

import com.vatva.blogapplication.domain.dto.ArticleCreateDto;
import com.vatva.blogapplication.domain.dto.ArticleResponseDto;
import com.vatva.blogapplication.domain.dto.ArticleUpdateDto;
import com.vatva.blogapplication.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    uses = UserMapper.class,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {

  ArticleResponseDto toDto(Article article);

  Article toEntity(ArticleCreateDto dto);

  void updateEntityFromDto(ArticleUpdateDto dto, @MappingTarget Article article);
}