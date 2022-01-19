package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Articles;
import com.kakao.cafe.repository.mapper.InMemoryArticleMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryArticleRepository implements ArticleRepository {

    private final Articles articles;
    private final InMemoryArticleMapper articleMapper;

    public InMemoryArticleRepository(InMemoryArticleMapper articleMapper) {
        this.articles = new Articles();
        this.articleMapper = articleMapper;
    }

    @Override
    public synchronized void save(Article article) {
        articles.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articles.getArticleList().stream()
                .map(articleMapper::mapResult)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Article> findArticleById(UUID id) {
        return articles.findByArticleId(id)
                .map(articleMapper::mapResult);
    }

    @Override
    public void update(Article article) {
        articles.update(article);
    }
}
