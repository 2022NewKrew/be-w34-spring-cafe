package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.util.ArticleRowMapper;
import com.kakao.cafe.util.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder;

    @Autowired
    public JdbcArticleRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        keyHolder = new GeneratedKeyHolder();
    }

    @Override
    public Article save(Article article) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);

        if (findById(article.getId()).isPresent()) {
            return update(article, namedParameters);
        }

        namedParameterJdbcTemplate.update(Sql.INSERT_ARTICLE, namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        article.setId(id);
        return article;
    }

    @Override
    public Long delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(Sql.DELETE_ARTICLE, namedParameters);
        return id;
    }

    @Override
    public Article increaseViewCount(Article article) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);
        namedParameterJdbcTemplate.update(Sql.UPDATE_ARTICLE_VIEW, namedParameters);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectArticleWhereCondition(Sql.FIND_ARTICLE_BY_ID, namedParameters);
    }

    @Override
    public Optional<Long> findUidById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(Sql.FIND_UID_BY_ID, namedParameters, Long.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(Sql.FIND_ALL_ARTICLE, new ArticleRowMapper());
    }

    private Optional<Article> selectArticleWhereCondition(String sql, SqlParameterSource namedParameters) {
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    sql, namedParameters, new ArticleRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Article update(Article article, SqlParameterSource namedParameters) {
        namedParameterJdbcTemplate.update(Sql.UPDATE_ARTICLE, namedParameters);
        return article;
    }
}
