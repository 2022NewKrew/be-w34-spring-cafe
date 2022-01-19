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

    public Article toEntity(int articleId) {
        Article article = Article.builder()
                .articleId(articleId)
                .writer(this.writer)
                .title(this.title)
                .contents(this.contents)
                .build();
        return article;
    }

}
