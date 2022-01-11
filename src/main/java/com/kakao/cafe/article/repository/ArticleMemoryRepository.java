package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.QuestionDTO;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ArticleMemoryRepository implements ArticleRepository {

    private static Map<Long, Article> articleMap = new TreeMap<>();

    @Override
    public Article save(QuestionDTO articleDTO) {
        Article article = new Article(articleMap.size() + 1L, articleDTO);
        articleMap.put(articleMap.size() + 1L, article);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleMap.get(id));
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleMap.values());
    }
}
