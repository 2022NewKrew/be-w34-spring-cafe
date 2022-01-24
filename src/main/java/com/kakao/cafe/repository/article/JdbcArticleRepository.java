package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

import static com.kakao.cafe.util.SqlArticle.*;

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

        namedParameterJdbcTemplate.update(INSERT_ARTICLE.query(), namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        article.setId(id);
        return article;
    }

    @Override
    public Long delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_ARTICLE.query(), namedParameters);
        return id;
    }

    @Override
    public Article increaseViewCount(Article article) {
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE_VIEW.query(), namedParameters);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectArticleWhereCondition(FIND_ARTICLE_BY_ID.query(), namedParameters);
    }

    @Override
    public Optional<Long> findUidById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(FIND_UID_BY_ID.query(), namedParameters, Long.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> findUserNicknameById(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    FIND_NICKNAME_BY_USERID.query(), namedParameters, String.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query(FIND_ALL_ARTICLE.query(), articleRowMapper());
    }

    private Optional<Article> selectArticleWhereCondition(String sql, SqlParameterSource namedParameters) {
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    sql, namedParameters, articleRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Article update(Article article, SqlParameterSource namedParameters) {
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE.query(), namedParameters);
        return article;
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) ->
                Article.builder()
                        .id(rs.getLong("id"))
                        .userId(rs.getLong("userId"))
                        .title(rs.getString("title"))
                        .body(rs.getString("body"))
                        .createdAt(rs.getTimestamp("createdAt").toLocalDateTime())
                        .views(rs.getInt("views"))
                        .build();
    }
}
