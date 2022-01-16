package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "writeDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "views", defaultValue = "0")
    ArticleEntity toArticleEntity(ArticleDto articleDto);

    @Mapping(source = "writeDate", target = "writeDate", dateFormat = "yyyy-MM-dd")
    ArticleDto toArticleDto(ArticleEntity articleEntity);
}
