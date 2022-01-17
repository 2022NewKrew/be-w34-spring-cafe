package com.kakao.cafe.web.dto;

import lombok.Getter;

@Getter
public class PostResponseDto {
    private String user;
    private String title;
    private String content;
    private Integer views;
    private Long id;
    private String date;

    public PostResponseDto(String user, String title, String content, int views, Long id, String date) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.views = views;
        this.id = id;
        this.date = date;
    }
}
