package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticlePostDto {
    private String writer;
    private String title;
    private String contents;

    public Article toEntity() {
        return new Article(0, writer, title, contents);
    }
}
