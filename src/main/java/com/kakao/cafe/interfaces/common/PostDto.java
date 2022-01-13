package com.kakao.cafe.interfaces.common;

import lombok.Data;

@Data
public class PostDto {
    private String writer;
    private String title;
    private String body;
    private String createdAt;
}
