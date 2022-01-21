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
    private final Boolean isWriter;

    public ArticleDetailResponse(Long id, String title, String nickname, String createdDate, Long viewNum,
        String content, Boolean isWriter) {
        this.id = id;
        this.title = title;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.viewNum = viewNum;
        this.content = content;
        this.isWriter = isWriter;
    }

    public static ArticleDetailResponse of(Article article, Long userId) {
        return new ArticleDetailResponse(article.getId(),
            article.getTitle(),
            article.getUser().getNickname(),
            article.getCreatedDate().format(DateTimeFormatter.ofPattern(CREATED_DATE_PATTERN)),
            article.getViewNum(), article.getContent(), article.validateAuth(userId));
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
            ", isWriter=" + isWriter +
            '}';
    }
}
