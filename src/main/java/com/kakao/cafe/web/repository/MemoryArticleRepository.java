package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.dto.ArticleCreateDTO;
import com.kakao.cafe.web.utility.CombinedSqlParameterSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Repository
public class MemoryArticleRepository implements ArticleRepository{
    private final Logger logger;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final RowMapper<Article> articleRowMapper = new BeanPropertyRowMapper<>(Article.class);

    public MemoryArticleRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Article save(ArticleCreateDTO articleCreateDTO) throws NullPointerException {
        // Save Article
        String sql = "INSERT INTO cafe_article (writer, title, contents, createdTime, modifiedTime) " +
                "VALUES ((SELECT user_id FROM cafe_user WHERE user_id = :writer), :title, :contents, :createdTimem, :modifiedTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        CombinedSqlParameterSource sqlParameterSource = new CombinedSqlParameterSource(articleCreateDTO);
        sqlParameterSource.addValue("createdTime", new Timestamp(System.currentTimeMillis()));
        sqlParameterSource.addValue("modifiedTime", new Timestamp(System.currentTimeMillis()));
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);

        // Return Created Article
        sql = "SELECT * FROM cafe_article WHERE id = :id";
        sqlParameterSource = new CombinedSqlParameterSource(null);
        sqlParameterSource.addValue("id", Objects.requireNonNull(keyHolder.getKey()).longValue());
        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, articleRowMapper);
    }

    @Override
    public List<Article> getArticleList() {
        String sql = "SELECT * FROM cafe_article";
        return namedParameterJdbcTemplate.query(sql, articleRowMapper);
    }

    @Override
    public Optional<Article> getArticleById(long id) {
        String sql = "SELECT * FROM cafe_article WHERE id = :id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id", id);
        List<Article> articleByIdList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, articleRowMapper);
        return articleByIdList.stream().findFirst();
    }
}
