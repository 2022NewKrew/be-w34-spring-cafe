package com.kakao.cafe.dto;

import lombok.Builder;

@Builder
public class PreviewArticleResponse {
    Long id;
    String title;
    String writer;
    String time;
    String replyCount;
}
