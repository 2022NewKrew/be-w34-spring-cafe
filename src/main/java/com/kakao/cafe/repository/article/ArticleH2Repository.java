package com.kakao.cafe.repository.article;

import com.kakao.cafe.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.constant.sql.ArticleSql.FIND_ALL;
import static com.kakao.cafe.constant.sql.ArticleSql.FIND_BY_ID;

@Repository
public class ArticleH2Repository implements ArticleRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<ArticleEntity> rowMapper;

    @Autowired
    ArticleH2Repository(DataSource dataSource, ArticleEntityRowMapper rowMapper) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("article")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper;
    }

    @Override
    public <S extends ArticleEntity> S save(S entity) {
        try {
            entity.initDate();
            SqlParameterSource params = new BeanPropertySqlParameterSource(entity);

            Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
            entity.putArticleId(id);

            return entity;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArticleEntity findOne(Long primaryKey) {
        Map<String, Long> params = Collections.singletonMap("primaryKey", primaryKey);

        return jdbcTemplate.queryForObject(FIND_BY_ID.getQuery(), params, rowMapper);
    }

    @Override
    public List<ArticleEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL.getQuery(), rowMapper);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(ArticleEntity entity) {

    }

    @Override
    public boolean exists(Long primaryKey) {
        return false;
    }
}
