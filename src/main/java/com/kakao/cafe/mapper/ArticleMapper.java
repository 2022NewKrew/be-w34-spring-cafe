package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleFormDto;
import com.kakao.cafe.dto.ArticleViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article        convertToEntity(ArticleFormDto articleFormDto);
    ArticleViewDto convertToArticleViewDto(Article article);
}
