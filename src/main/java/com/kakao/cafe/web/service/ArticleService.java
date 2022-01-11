package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArticleService {
    private final List<Article> articleList = new ArrayList<>();
    private Long id = 0L;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void addArticle(Article article) {
        article.setId(id);
        id++;
        article.setCreatedAt(LocalDateTime.now());
        articleList.add(article);
    }

    public Article getByArticleId(Long id) {
        for (Article article : articleList) {
            if (Objects.equals(article.getId(), id))
                return article;
        }
        throw new IllegalArgumentException("해당 id를 가진 글이 존재하지 않습니다.");
    }
}
