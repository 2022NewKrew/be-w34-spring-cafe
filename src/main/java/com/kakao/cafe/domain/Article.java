package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Article {
    private int id;
    private String writer;  // FK references MEMBER.userId
    private String title;
    private String contents;
}
