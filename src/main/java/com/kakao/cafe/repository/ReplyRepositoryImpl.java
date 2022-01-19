package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReplyRepositoryImpl implements ReplyRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ReplyRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Reply").usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Reply reply) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("content", reply.getContent())
                .addValue("createdAt", reply.getCreatedAt())
                .addValue("postId", reply.getPost().getId())
                .addValue("isRemoved", reply.isRemoved())
                .addValue("writerId", reply.getWriter().getId());

        simpleJdbcInsert.executeAndReturnKey(params);
    }
}
