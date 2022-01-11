package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.User;

public class DraftDto {

    private final String author;
    private final String title;
    private final String content;

    public DraftDto(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Draft toEntity(User owner) {
        return new Draft(owner, author, title, content);
    }
}
