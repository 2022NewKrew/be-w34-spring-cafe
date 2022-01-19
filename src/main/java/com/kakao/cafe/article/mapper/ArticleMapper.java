package com.kakao.cafe.article.mapper;

import com.kakao.cafe.article.dto.ArticleDto;
import com.kakao.cafe.article.entity.Article;
import com.kakao.cafe.common.GenericMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper extends GenericMapper<ArticleDto, Article> {
}
