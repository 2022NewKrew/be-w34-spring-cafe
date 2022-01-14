package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticlesResponse {

    private final List<ArticleResponse> articleResponses;

    private ArticlesResponse(List<ArticleResponse> articleResponses) {
        this.articleResponses = new ArrayList<>(articleResponses);
    }

    public static ArticlesResponse of(List<Article> articles) {
        return new ArticlesResponse(articles.stream()
            .map(ArticleResponse::of)
            .collect(Collectors.toList()));
    }
}
