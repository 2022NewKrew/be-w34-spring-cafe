package com.kakao.cafe.service.dto;

import com.kakao.cafe.domain.entity.Draft;
import com.kakao.cafe.domain.entity.User;

public class DraftDto {

    private final String title;
    private final String content;

    public DraftDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Draft toEntity(User author) {
        return new Draft(author, title, content);
    }
}
