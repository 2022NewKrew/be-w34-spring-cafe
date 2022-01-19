package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReplyDTO {
    private final String articleId;
    private final String reviewId;
    private final String writer;
    private final String writerProfileAddress;
    private final String writtenTime;
    private final String content;
}
