package com.kakao.cafe.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {
    private int commentId;
    private final int articleId;
    private final String userId;
    private final String writer;
    private final String contents;
}
