package com.kakao.cafe.article.application.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ArticleSaveRequest {

    public final String writer;
    public final String title;
    public final String contents;

    public Article toArticle(String userId) {
        return Article.valueOf(userId, title, contents);
    }
}
