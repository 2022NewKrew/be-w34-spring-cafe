package com.kakao.cafe.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Getter
public class Reply {
    private final int id;
    private final String userId;
    private final String userName;
    private final int articleId;
    private final String content;
    private final Timestamp createdAt;
}
