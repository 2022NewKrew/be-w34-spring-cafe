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
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ArticleRowMapper articleRowMapper;

    public FindArticleH2Adaptor(NamedParameterJdbcTemplate jdbcTemplate, ArticleRowMapper articleRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.articleRowMapper = articleRowMapper;
    }

    @Override
    public List<Article> findAll() {
        SqlParameterSource parameters = new MapSqlParameterSource();

        List<Article> articles = jdbcTemplate.query(H2ArticleQueryBuilder.selectAll(), parameters, articleRowMapper);
        return articles;
    }

    @Override
    public Optional<Article> findById(int index) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("article_id", index);

        try {
            return Optional.of(jdbcTemplate.queryForObject(H2ArticleQueryBuilder.selectOneByField("article_id"), parameters, articleRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
