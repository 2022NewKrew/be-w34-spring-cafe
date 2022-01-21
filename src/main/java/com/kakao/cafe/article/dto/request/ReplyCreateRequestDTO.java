package com.kakao.cafe.article.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.kakao.cafe.article.entity.Reply;


@RequiredArgsConstructor
@Getter
public class ReplyCreateRequestDTO {
    private int articleId;
    private String writer;
    private final String contents;

    public Reply toEntity() {
        return Reply.builder()
                    .articleId(articleId)
                    .writer(writer)
                    .contents(contents)
                    .build();
    }

    public void init(int articleId, String writer) {
        this.articleId = articleId;
        this.writer = writer;
    }
}
