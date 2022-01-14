package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Contents;
import com.kakao.cafe.domain.Title;
import com.kakao.cafe.domain.UserId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleMapper {

    public static Article toArticle(ArticleFormDto articleFormDto) {
        UserId writerId = new UserId(articleFormDto.getWriter());
        Title title = new Title(articleFormDto.getTitle());
        Contents contents = new Contents(articleFormDto.getContents());
        return new Article(writerId, new Date(), title, contents);
    }

    public static List<ArticleListDto> toListArticleDto(List<Article> articles) {
        return articles.stream()
                .map(article -> new ArticleListDto(article.getWriterId(), article.getWriteTime(), article.getTitle()))
                .collect(Collectors.toList());
    }

    public static ArticleDto toArticleDto(Article article) {
        return new ArticleDto(article.getWriterId(), article.getWriteTime(), article.getTitle(), article.getContents());
    }
}
