package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepositoryJdbcImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Article> articleMapper = (rs, rowNum) -> {
        Article article = new Article(
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("content")
        );
        article.setId(rs.getLong("id"));
        article.setCreationTime(rs.getDate("creation_time"));
        return article;
    };

    @Autowired
    public ArticleRepositoryJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Article article) {
        String INSERT_USER = "INSERT INTO ARTICLE (WRITER, TITLE, CONTENT) " +
                "VALUES ('%s', '%s', '%s');";
        jdbcTemplate.execute(String.format(
                INSERT_USER, article.getWriter(), article.getTitle(), article.getContent())
        );
    }

    @Override
    public List<Article> findAll() {
        String SELECT_ARTICLES = "SELECT ID, WRITER, TITLE, CONTENT, CREATION_TIME FROM ARTICLE";
        return jdbcTemplate.query(SELECT_ARTICLES, articleMapper);
    }

    @Override
    public Optional<Article> findById(Long id) {
        String SELECT_ARTICLE = "SELECT ID, WRITER, TITLE, CONTENT, CREATION_TIME " +
                "FROM ARTICLE " +
                "WHERE ID=%d";
        List<Article> repositoryArticles = jdbcTemplate.query(String.format(SELECT_ARTICLE, id), articleMapper);
        return repositoryArticles.stream().findFirst();
    }
}
