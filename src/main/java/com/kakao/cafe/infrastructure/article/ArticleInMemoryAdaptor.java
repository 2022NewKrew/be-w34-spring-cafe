package com.kakao.cafe.infrastructure.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ArticleInMemoryAdaptor implements ArticlePort {
    private static final List<Article> articles = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger index = new AtomicInteger();

    @Override
    public void save(ArticleVo article) {
        articles.add(
                new Article(index.incrementAndGet(),
                        article.getWriter(),
                        LocalDateTime.now(),
                        article.getTitle(),
                        article.getContents()));
    }

    @Override
    public List<Article> findAll() {
        return Collections.unmodifiableList(articles);
    }

    @Override
    public Optional<Article> findById(int index) {
        if (index - 1 >= articles.size()) {
            return Optional.empty();
        }
        return Optional.of(articles.get(index - 1));
    }
}
