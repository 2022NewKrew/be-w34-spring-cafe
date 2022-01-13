package com.kakao.cafe.domain.article;

import com.kakao.cafe.web.dto.article.ArticleAddRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private final int index;
    private final String createdDate;
    private final String createdTime;
    private final String writer;
    private final String title;
    private final String contents;

    Article(int index, String createdDate, String createdTime, ArticleAddRequestDto artDto) {
        this.index = index;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.writer = artDto.getWriter();
        this.title = artDto.getTitle();
        this.contents = artDto.getContents();
    }
}
