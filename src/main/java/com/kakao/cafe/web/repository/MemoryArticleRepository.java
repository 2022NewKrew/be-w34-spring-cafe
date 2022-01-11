package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleDTO;

import java.util.*;

public class MemoryArticleRepository implements ArticleRepository{
    Map<Integer, Article> articleMap;

    public MemoryArticleRepository() {
        this.articleMap = new HashMap<>();
    }

    @Override
    public Article save(ArticleDTO articleDTO) {
        int id = articleMap.size() + 1;
        Article article = new Article(id,
                articleDTO.getWriter(),
                articleDTO.getTitle(),
                articleDTO.getContents());
        articleMap.put(id, article);
        return article;
    }

    @Override
    public List<Article> getArticleList() {
        return new ArrayList<>(articleMap.values());
    }

    @Override
    public Article getArticleById(int id) {
        Optional<Article> foundArticle = articleMap.values().stream()
                .filter(article -> article.getId() == id)
                .findFirst();
        return foundArticle.orElse(null);
    }
}
