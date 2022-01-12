package com.kakao.cafe.domain.post;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Post {
    private Long id;
    private String writer;
    private String title;
    private String content;
}
