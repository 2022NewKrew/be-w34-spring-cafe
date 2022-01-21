package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;
import lombok.Builder;
import lombok.ToString;

@ToString
public class ArticleUpdateResponse {

    private final Long id;
    private final String title;
    private final String nickname;
    private final String content;

    @Builder
    private ArticleUpdateResponse(Long id, String title, String nickname, String content) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }

    public static ArticleUpdateResponse of(Article article) {
        return ArticleUpdateResponse.builder()
            .id(article.getId())
            .title(article.getTitle())
            .nickname(article.getUser().getNickname())
            .content(article.getContent())
            .build();
    }
}
