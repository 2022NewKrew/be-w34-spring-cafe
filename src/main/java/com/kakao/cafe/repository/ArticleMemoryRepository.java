package com.kakao.cafe.repository;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleMemoryRepository implements ArticleRepository {
    private final List<Article> articleList = new ArrayList<>();
    private Integer articleSequence = 0;

    @Override
    public void save(Article article){
        article.setId(articleSequence++);
        articleList.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articleList;
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return Optional.of(articleList.get(id));
    }
}
