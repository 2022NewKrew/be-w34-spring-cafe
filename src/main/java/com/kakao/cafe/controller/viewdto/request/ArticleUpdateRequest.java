package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleUpdateRequest {
    private final String title;
    private final String contents;
    private final String articleId;
}
