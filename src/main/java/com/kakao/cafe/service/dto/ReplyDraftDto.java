package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.domain.entity.ReplyDraft;
import com.kakao.cafe.domain.entity.User;

public class ReplyDraftDto {

    private final String content;

    public ReplyDraftDto(String content) {
        this.content = content;
    }

    public ReplyDraft toEntity(Article target, User author) {
        return new ReplyDraft(target, author, content);
    }
}
