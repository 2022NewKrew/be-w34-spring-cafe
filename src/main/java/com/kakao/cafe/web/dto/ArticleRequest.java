package com.kakao.cafe.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class ArticleRequest {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    @Builder
    private ArticleRequest(long id, String title, String content, String createUserId, String createDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createUserId = createUserId;
        this.createDate = createDate;
        this.views = views;
    }

    public static ArticleRequest newInstance(String title, String content, String createUserId) {
        return ArticleRequest.builder()
                .title(title)
                .content(content)
                .createUserId(createUserId)
                .createDate(LocalDate.now().toString())
                .views(0)
                .build();
    }
}
