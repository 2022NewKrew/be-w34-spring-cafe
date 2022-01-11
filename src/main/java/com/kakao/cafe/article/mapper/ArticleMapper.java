package com.kakao.cafe.article.mapper;

import com.kakao.cafe.article.dto.response.ArticleResDto;
import com.kakao.cafe.article.entity.ArticleEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public List<ArticleResDto> toDtoList(List<ArticleEntity> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ArticleResDto toDto(ArticleEntity articleEntity) {
        return ArticleResDto.builder()
                .title(articleEntity.getTitle())
                .writer(articleEntity.getWriter())
                .createdAt(articleEntity.getCreatedAt())
                .numComments(articleEntity.getComments().size())
                .build();
    }
}
