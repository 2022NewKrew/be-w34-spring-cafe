package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.util.ArticleMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcArticleRepository implements Repository<Article, Integer> {

    private final JdbcTemplate jdbcTemplate;
    private final ArticleMapper articleMapper;

    public JdbcArticleRepository(JdbcTemplate jdbcTemplate, ArticleMapper articleMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleMapper = articleMapper;
    }

    @Override
    public void create(Article article) {
        String sqlQuery = "insert into ARTICLE (title, content) values (?, ?)";
        jdbcTemplate.update(sqlQuery, article.getTitle(), article.getContent());
    }

    @Override
    public List<Article> readAll() {
        return jdbcTemplate.query("SELECT * FROM ARTICLE", articleMapper);
    }

    @Override
    public Optional<Article> readById(Integer id) {
        String sqlQuery = "SELECT * FROM ARTICLE WHERE id = ?";
        Article article = jdbcTemplate.queryForObject(sqlQuery, articleMapper, id);
        return Optional.ofNullable(article);
    }
}
