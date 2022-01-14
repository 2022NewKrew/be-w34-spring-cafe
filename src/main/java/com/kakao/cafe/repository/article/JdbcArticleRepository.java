package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Article article) {

    }

    @Override
    public Optional<Article> findByArticleId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return null;
    }
}
