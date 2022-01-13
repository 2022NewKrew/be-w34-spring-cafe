package com.kakao.cafe.reply.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReplyCreateRequestDTO {
    public Long articleId;
    public Long authorId;
    public String contents;
}
