package com.kakao.cafe.reply.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Reply {
    private Long replayId;
    private Long articleId;
    private Long authorId;
    private String contents;
}
