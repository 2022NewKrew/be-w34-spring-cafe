package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;

public class ArticleUpdateResponse {

    private final Long id;
    private final String title;
    private final String nickname;
    private final String content;

    private ArticleUpdateResponse(Long id, String title, String nickname, String content) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }

    public static ArticleUpdateResponse of(Article article) {
        return new ArticleUpdateResponse(article.getId(),
            article.getTitle(),
            article.getUser().getNickname(),
            article.getContent());
    }

    @Override
    public String toString() {
        return "ArticleUpdateResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", nickname='" + nickname + '\'' +
            ", content='" + content + '\'' +
            '}';
    }
}
