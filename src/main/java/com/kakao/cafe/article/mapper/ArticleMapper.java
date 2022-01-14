package com.kakao.cafe.article.mapper;

import com.kakao.cafe.article.dto.request.ArticleCreateRequest;
import com.kakao.cafe.article.dto.response.ArticleDetailResponse;
import com.kakao.cafe.article.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mappings({
        @Mapping(target = "id", constant = "0L"),
        @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    })
    Article articleCreateRequestToEntity(ArticleCreateRequest articleCreateRequest);
    ArticleDetailResponse articleToArticleDetailResponse(Article article);
}
