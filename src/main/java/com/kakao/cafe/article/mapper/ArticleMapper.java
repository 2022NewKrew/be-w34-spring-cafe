package com.kakao.cafe.article.mapper;

import com.kakao.cafe.article.dto.response.ArticleDetailResDto;
import com.kakao.cafe.article.dto.response.ArticleResDto;
import com.kakao.cafe.article.entity.ArticleEntity;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    public List<ArticleResDto> toArticleResDtoList(List<ArticleEntity> entityList) {
        return entityList.stream()
                .map(this::toArticleResDto)
                .collect(Collectors.toList());
    }

    public ArticleResDto toArticleResDto(ArticleEntity articleEntity) {
        return ArticleResDto.builder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .writer(articleEntity.getWriter())
                .createdAt(articleEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .numComments(articleEntity.getComments().size())
                .build();
    }

    public ArticleDetailResDto toArticleDetailResDto(ArticleEntity articleEntity) {
        return ArticleDetailResDto.builder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .writer(articleEntity.getWriter())
                .contents(articleEntity.getContents())
                .createdAt(articleEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
}
