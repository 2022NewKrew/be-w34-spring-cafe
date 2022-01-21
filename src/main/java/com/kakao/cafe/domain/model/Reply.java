package com.kakao.cafe.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Reply {
    private final int id;
    private final String userId;
    private final int articleId;
    private final String content;
}
