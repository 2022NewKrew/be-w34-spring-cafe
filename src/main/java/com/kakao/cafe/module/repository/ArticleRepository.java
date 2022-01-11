package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final List<Article> articleList = new ArrayList<>();

    public void addArticle(Article article) {
        articleList.add(article);
    }

    public List<Article> findAllArticles() {
        return articleList;
    }

    public Article findArticleById(Long id) {
        return articleList.stream()
                .filter(article -> article.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }
}
