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
        final String INSERT_ARTICLE = "INSERT INTO `ARTICLE`(userId, title, body, createdAt) VALUES(:userId, :title, :body, :createdAt)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);

        if (findById(article.getId()).isPresent()) {
            return update(article, namedParameters);
        }

        namedParameterJdbcTemplate.update(INSERT_ARTICLE, namedParameters, keyHolder, new String[]{"id"});
        long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        article.setId(id);
        return article;
    }

    @Override
    public Long delete(Long id) {
        final String DELETE_ARTICLE = "DELETE FROM `ARTICLE` where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE_ARTICLE, namedParameters);
        return id;
    }

    @Override
    public Article increaseViewCount(Article article) {
        final String UPDATE_ARTICLE_VIEW = "UPDATE `ARTICLE` SET views=:views WHERE id=:id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE_VIEW, namedParameters);
        return article;
    }

    @Override
    public Optional<Article> findById(Long id) {
        final String FIND_ARTICLE_BY_ID = "SELECT * FROM `ARTICLE` WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return selectArticleWhereCondition(FIND_ARTICLE_BY_ID, namedParameters);
    }

    @Override
    public Optional<Long> findUidById(Long id) {
        final String FIND_UID_BY_ID = "SELECT userId FROM `ARTICLE` WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(FIND_UID_BY_ID, namedParameters, Long.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> findUserNicknameById(Long id) {
        final String FIND_NICKNAME_BY_USERID = "SELECT USER.nickname FROM `ARTICLE` JOIN `USER` ON USER.id = ARTICLE.userId WHERE ARTICLE.id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        try {
            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
                    FIND_NICKNAME_BY_USERID, namedParameters, String.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Article> findAll() {
        final String FIND_ALL_ARTICLE = "SELECT * FROM `ARTICLE`";
        return jdbcTemplate.query(FIND_ALL_ARTICLE, articleRowMapper());
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
        final String UPDATE_ARTICLE = "UPDATE `ARTICLE` SET title=:title, body=:body WHERE id=:id";
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE, namedParameters);
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
