package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;

public class ArticleResponse {

    private final Long id;
    private final String title;
    private final String nickname;
    private final String createdDate;
    private final Long viewNum;

    private ArticleResponse(Long id, String title, String nickname, String createdDate, Long viewNum) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.viewNum = viewNum;
    }

    public static ArticleResponse of(Article article) {
        return new ArticleResponse(article.getId(),
            article.getTitle(),
            article.getUser().getNickname(),
            article.getCreatedDate().toLocalDate().toString(),
            article.getViewNum());
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", nickname='" + nickname + '\'' +
            ", createDate='" + createdDate + '\'' +
            ", viewNum=" + viewNum +
            '}';
    }
}
