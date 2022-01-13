package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleResponse {
    private Long articleId;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;


    private ArticleResponse() {}

    public static ArticleResponse from(Article article) {
        ArticleResponse responseDto = new ArticleResponse();
        responseDto.setArticleId(article.getArticleId());
        responseDto.setTitle(article.getTitle());
        responseDto.setWriter(article.getWriter());
        responseDto.setContent(article.getContent());
        responseDto.setCreatedAt(article.getCreatedAt());
        responseDto.setNumOfComment(article.getNumOfComment());
        return responseDto;
    }
}
