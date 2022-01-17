package com.kakao.cafe.article.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleReadServiceResponse {
    private Long id;
    private Long authorId;
    private String authorStringId;
    private String title;
    private String contents;
    private LocalDateTime makeTime;
    private Integer hits;

    @Builder
    private ArticleReadServiceResponse(Long id, Long authorId, String authorStringId, String title, String contents, LocalDateTime makeTime, Integer hits) {
        this.id = id;
        this.authorId = authorId;
        this.authorStringId = authorStringId;
        this.title = title;
        this.contents = contents;
        this.makeTime = makeTime;
        this.hits = hits;
    }
}
