package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ArticleRegistrationDto;
import com.kakao.cafe.entity.Article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ArticleRepository {
    private final List<Article> articles = Collections.synchronizedList(new ArrayList<>());

    public void createArticle(ArticleRegistrationDto articleRequestDto) {
//        Article article = new Article(articles.size()+1, articleRequestDto.getTitle(), articleRequestDto.getContent());
//        articles.add(article);
    }

    public List<Article> readArticles() {
        return articles;
    }

    public Article readArticle(Integer articleId) {
        return articles.stream()
                .filter(article -> Objects.equals(article.getId(), articleId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
