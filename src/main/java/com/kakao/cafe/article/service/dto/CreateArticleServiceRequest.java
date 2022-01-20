package com.kakao.cafe.article.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateArticleServiceRequest {
    private Long authorId;
    private String authorStringId;
    private String title;
    private String contents;

    @Builder
    private CreateArticleServiceRequest(Long authorId, String authorStringId, String title, String contents) {
        this.authorId = authorId;
        this.authorStringId = authorStringId;
        this.title = title;
        this.contents = contents;
    }
}
