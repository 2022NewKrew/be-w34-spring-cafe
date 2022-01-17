package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private static final List<Article> articles = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong idx = new AtomicLong(0);

    static {
        articles.add(new Article(0L, "국내에서 Ruby on Rails와 Play가 활성화되기 힘든 이유는 뭘까?",
            "호눅스가 요청하신 페이지의 디자인 입니다.\n각각의 글의 상세 페이지는 이런 느낌이네요!",
            new User(0L, "aa@aa.com", "멋진삼", "aaaa")));
    }

    @Override
    public Long autoIncrement() {
        return idx.incrementAndGet();
    }

    @Override
    public Article save(Article article) {
        articles.add(article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return articles;
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articles.stream()
            .filter(article -> article.getId().equals(id))
            .findFirst();
    }
}
