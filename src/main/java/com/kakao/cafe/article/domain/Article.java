package com.kakao.cafe.article.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Article {

    private Long id;
    private String title;
    private String content;
    private LocalDate createDate;
    private Long memberId;
    private Boolean deleted;

    public void setId(Long id) {
        this.id = id;
    }
}
