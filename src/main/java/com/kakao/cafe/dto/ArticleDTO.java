package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class ArticleDTO {
    private final Long id;
    @NotNull
    private final Long writerId;
    private final String writer;

    @Size(min = 1, max = 100)
    private final String title;

    @Size(min = 1, max = 1000)
    private final String contents;

    private final Long views;
    private final String time;

}
