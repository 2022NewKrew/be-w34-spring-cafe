package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleCreateRequest {
    private final String title;
    private final String contents;

}
