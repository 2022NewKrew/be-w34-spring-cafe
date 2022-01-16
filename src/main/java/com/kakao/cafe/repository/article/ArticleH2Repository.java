package com.kakao.cafe.repository.article;

import com.kakao.cafe.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class ArticleH2Repository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    ArticleH2Repository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("article")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public <S extends ArticleEntity> S save(S entity) {
        try {
            entity.putCreatedDate();
            entity.putUpdatedDate();

            SqlParameterSource params = new MapSqlParameterSource()
                    .addValue("title", entity.getTitle())
                    .addValue("content", entity.getContent())
                    .addValue("writer", entity.getWriter())
                    .addValue("views", entity.getViews())
                    .addValue("write_date", Timestamp.valueOf(entity.getWriteDate()))
                    .addValue("created_date", Timestamp.valueOf(entity.getCreatedDate()))
                    .addValue("updated_date", Timestamp.valueOf(entity.getUpdatedDate()));

            Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
            entity.putArticleId(id);

            return entity;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ArticleEntity findOne(Long primaryKey) {
        String sql = "select * from article where id = " + primaryKey;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new ArticleEntity(rs.getLong("id"), rs.getString("title"),
                        rs.getString("content"), rs.getString("writer"),
                        rs.getTimestamp("write_date").toLocalDateTime(), rs.getInt("views"))).get(0);
    }

    @Override
    public List<ArticleEntity> findAll() {
        String sql = "select * from article";

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new ArticleEntity(rs.getLong("id"), rs.getString("title"),
                        rs.getString("content"), rs.getString("writer"),
                        rs.getTimestamp("write_date").toLocalDateTime(), rs.getInt("views")));
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
