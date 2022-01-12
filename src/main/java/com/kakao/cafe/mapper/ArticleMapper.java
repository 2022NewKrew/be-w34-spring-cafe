package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.service.ArticleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses= ArticleService.class)
public interface ArticleMapper {
    @Mapping(target = "id", ignore = true)
    Article toEntity(ArticleDto user);
}