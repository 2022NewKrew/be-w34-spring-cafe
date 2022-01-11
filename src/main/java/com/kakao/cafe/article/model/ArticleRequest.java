package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ArticleRequest {
    private final String author;
    private final String title;
    private final String contents;

    public Article toEntity(Long id){
        return Article.builder()
                .id(id)
                .author(author)
                .title(title)
                .contents(contents)
                .uploadTime((new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date()))
                .build();
    }
}
