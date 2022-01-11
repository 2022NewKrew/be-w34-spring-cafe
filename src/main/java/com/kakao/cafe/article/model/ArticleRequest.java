package com.kakao.cafe.article.model;

import com.kakao.cafe.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
                .uploadTime(DateUtils.getCurrentDate())
                .build();
    }
}
