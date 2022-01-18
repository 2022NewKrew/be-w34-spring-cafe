package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleLocalRepository implements ArticleRepository {
    List<Article> articleList;

    public ArticleLocalRepository() {
        articleList = new ArrayList<>();
    }

    @Override
    public Optional<Long> save(Article article) {
        int curSize = articleList.size() + 1;
        article.setId((long) curSize);
        articleList.add(article);
        return Optional.ofNullable((long) curSize);
    }

    @Override
    public List<Article> getAllQuestions() {
        return articleList;
    }

    @Override
    public Article findById(String id) {
        return articleList.stream()
                .filter(x -> x.getId().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 Id 입니다");
                });
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteByWriter(String writer) {

    }

    @Override
    public void update(Article article) {

    }
}
