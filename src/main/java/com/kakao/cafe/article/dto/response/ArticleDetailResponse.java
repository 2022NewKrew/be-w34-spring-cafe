package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;
import java.time.format.DateTimeFormatter;

public class ArticleDetailResponse {
    private static final String CREATED_DATE_PATTERN = "YYYY-MM-dd HH:mm";

    private final Long id;
    private final String title;
    private final String nickname;
    private final String createdDate;
    private final Long viewNum;
    private final String content;

    private ArticleDetailResponse(Long id, String title, String nickname, String createdDate, Long viewNum,
        String content) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.viewNum = viewNum;
        this.content = content;
    }

    public static ArticleDetailResponse of(Article article) {
        return new ArticleDetailResponse(article.getId(),
            article.getTitle(),
            article.getUser().getNickname(),
            article.getCreatedDate().format(DateTimeFormatter.ofPattern(CREATED_DATE_PATTERN)),
            article.getViewNum(), article.getContent());
    }

    @Override
    public String toString() {
        return "ArticleDetailResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", nickname='" + nickname + '\'' +
            ", createdDate='" + createdDate + '\'' +
            ", viewNum=" + viewNum +
            ", content='" + content + '\'' +
            '}';
    }
}
