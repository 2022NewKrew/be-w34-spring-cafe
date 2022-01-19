package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleSummaryDTO {
    private final String title;
    private final String writer;
    private final String date;
    private final int point;
    private final String index;
}
