package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.util.UtilClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepository implements RepositoryInterface<Article> {
    private static final List<Article> articleList = new ArrayList<>();
    private static final AtomicLong sequence = new AtomicLong();


    @Override
    public void save(Article article) {
        article.setIndex(sequence.incrementAndGet());
        article.setDate(UtilClass.getLocalDateTimeNow());
        articleList.add(article);
    }

    @Override
    public Optional<Article> findById(Long index) {
        return articleList.stream()
                .filter(article -> article.getIndex().equals(index))
                .findAny();
    }

    @Override
    public Optional<Article> findByName(String title) {
        return articleList.stream()
                .filter(article -> article.getTitle().equals(title))
                .findAny();
    }

    @Override
    public List<Article> findAll() {
        return articleList;
    }
}
