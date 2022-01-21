package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;
import lombok.Builder;
import lombok.ToString;

@ToString
public class ArticleResponse {

    private final Long id;
    private final String title;
    private final String nickname;
    private final String createdDate;
    private final Long viewNum;

    @Builder
    private ArticleResponse(Long id, String title, String nickname, String createdDate, Long viewNum) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.viewNum = viewNum;
    }

    public static ArticleResponse of(Article article) {
        return ArticleResponse.builder()
            .id(article.getId())
            .title(article.getTitle())
            .nickname(article.getUser().getNickname())
            .createdDate(article.getCreatedDate().toLocalDate().toString())
            .viewNum(article.getViewNum())
            .build();
    }
}
