package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostArticleDto {
    private String writer;
    private String title;
    private String contents;

    public Article toEntity() {
        Article article = Article.builder()
                .writer(this.writer)
                .title(this.title)
                .contents(this.contents)
                .build();
        return article;
    }

}
