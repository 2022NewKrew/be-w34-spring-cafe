package com.kakao.cafe.reply.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateReplyRequestDTO {
    public Long articleId;
    public Long authorId;
    public String contents;
}
