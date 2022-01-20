package com.kakao.cafe.persistence.article.mysql.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.FindUserPort;
import org.springframework.stereotype.Component;

@Component
public class ArticleEntityDtoMapper {

    private final FindUserPort findUserPort;

    public ArticleEntityDtoMapper(FindUserPort findUserPort) {
        this.findUserPort = findUserPort;
    }

    public Article convertDtoToEntity(ArticleTableDto articleTableDto) {
        return Article.builder()
                .id(articleTableDto.getArticleId())
                .writer(findUserPort.findByUserId(articleTableDto.getUserId()).get())
                .createdAt(articleTableDto.getArticleCreatedAt())
                .title(articleTableDto.getArticleTitle())
                .contents(articleTableDto.getArticleContents())
                .build();
    }
}
