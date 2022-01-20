package com.kakao.cafe.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.kakao.cafe.article.entity.ArticleReply;

@AllArgsConstructor
@Getter
public class ArticleReplyCreateRequestDTO {
    private final int articleId;
    private final String writer;
    private final String contents;

    public ArticleReply toEntity() {
        return ArticleReply.builder()
                           .articleId(articleId)
                           .writer(writer)
                           .contents(contents)
                           .build();
    }
}
