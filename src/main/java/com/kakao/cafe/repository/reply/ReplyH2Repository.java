package com.kakao.cafe.repository.reply;

import com.kakao.cafe.constant.sql.ReplySql;
import com.kakao.cafe.entity.ReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.constant.sql.ArticleSql.FIND_ALL;
import static com.kakao.cafe.constant.sql.ArticleSql.UPDATE_BY_ID;
import static com.kakao.cafe.constant.sql.ReplySql.DELETE_BY_ID;
import static com.kakao.cafe.constant.sql.ReplySql.FIND_BY_ARTICLE_ID;

@Repository
public class ReplyH2Repository implements ReplyRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final RowMapper<ReplyEntity> rowMapper;

    @Autowired
    ReplyH2Repository(DataSource dataSource, ReplyEntityRowMapper rowMapper) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("reply")
                .usingGeneratedKeyColumns("id");
        this.rowMapper = rowMapper;
    }

    @Override
    public <S extends ReplyEntity> S save(S entity) {
        try {
            entity.initDate();
            SqlParameterSource params = new BeanPropertySqlParameterSource(entity);

            Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
            entity.putReplyId(id);

            return entity;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ReplyEntity findOne(Long primaryKey) {
        return null;
    }

    @Override
    public List<ReplyEntity> findAll() {
        return jdbcTemplate.query(FIND_ALL.getQuery(), rowMapper);
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(ReplyEntity entity) {

    }

    @Override
    public boolean exists(Long primaryKey) {
        return false;
    }

    @Override
    public void deleteById(Long primaryKey) {
        Map<String, String> params = Collections.singletonMap("primaryKey", Long.toString(primaryKey));

        jdbcTemplate.update(DELETE_BY_ID.getQuery(), params);

        return;
    }

    @Override
    public List<ReplyEntity> findByReplyId(Long articleId) {
        Map<String, Long> params = Collections.singletonMap("articleId", articleId);

        return jdbcTemplate.query(FIND_BY_ARTICLE_ID.getQuery(), params, rowMapper);
    }
}
