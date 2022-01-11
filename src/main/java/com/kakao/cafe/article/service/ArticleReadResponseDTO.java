package com.kakao.cafe.article.service;

import com.kakao.cafe.reply.domain.Reply;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
public class ArticleReadResponseDTO {
    public Long articleId;
    public Long authorId;
    public LocalDateTime makeTime;
    public Integer hits;
    public String contents;
}
