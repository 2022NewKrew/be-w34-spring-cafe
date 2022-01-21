package com.kakao.cafe.article.dto.response;

import com.kakao.cafe.article.domain.Article;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.ToString;

@ToString
public class ArticleDetailResponse {

    private static final String CREATED_DATE_PATTERN = "YYYY-MM-dd HH:mm";

    private final Long id;
    private final String title;
    private final String nickname;
    private final String createdDate;
    private final Long viewNum;
    private final String content;
    private final Boolean isWriter;

    @Builder
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
        String createdDate = article.getCreatedDate().format(DateTimeFormatter.ofPattern(CREATED_DATE_PATTERN));
        return ArticleDetailResponse.builder()
            .id(article.getId())
            .title(article.getTitle())
            .nickname(article.getUser().getNickname())
            .createdDate(createdDate)
            .viewNum(article.getViewNum())
            .content(article.getContent())
            .isWriter(article.validateAuth(userId))
            .build();
    }
}
