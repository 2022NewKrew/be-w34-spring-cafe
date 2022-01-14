package com.kakao.cafe.persistence.article.h2;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.FindArticlePort;
import com.kakao.cafe.persistence.article.ArticleRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FindArticleH2Adaptor implements FindArticlePort {
    private static final String TABLE_NAME = "ARTICLE";
    private static final List<String> FIELDS = List.of("article_id", "article_writer", "article_created_at", "article_title", "article_contents");
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;

    public FindArticleH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, ArticleRowMapper articleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleRowMapper = articleRowMapper;
    }

    @Override
    public List<Article> findAll() {
        String SELECT_ALL = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME;

        SqlParameterSource parameters = new MapSqlParameterSource();

        List<Article> articles = jdbcTemplate.query(SELECT_ALL, parameters, articleRowMapper);
        return articles;
    }

    @Override
    public Optional<Article> findById(int index) {
        String SELECT_BY_USER_ID = "SELECT " + String.join(", ", FIELDS) + " FROM " + TABLE_NAME + " WHERE article_id = :articleId LIMIT 1";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("articleId", index);

        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_BY_USER_ID, parameters, articleRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
