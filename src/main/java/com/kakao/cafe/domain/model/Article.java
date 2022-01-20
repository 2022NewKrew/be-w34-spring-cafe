package com.kakao.cafe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class Article {
    private final int id;
    private final String title;
    private final String content;
    private final String userId;
    private final String name; // user name
    private final Timestamp createdAt;
}
