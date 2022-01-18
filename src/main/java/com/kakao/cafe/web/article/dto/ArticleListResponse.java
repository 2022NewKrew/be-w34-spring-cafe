package com.kakao.cafe.web.article.dto;

import java.util.List;

public class ArticleListResponse {
    private final List<ArticleResponse> articles;

    public ArticleListResponse(List<ArticleResponse> articleResponses) {
        this.articles = articleResponses;
    }

    public List<ArticleResponse> getArticles() {
        return articles;
    }

    @Override
    public int hashCode() {
        return articles.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof ArticleListResponse)) {
            return false;
        }

        ArticleListResponse other = (ArticleListResponse) obj;
        return articles.equals(other.getArticles());
    }
}
