package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReplyDBRepository implements ReplyRepository{

    private final JdbcTemplate jdbcTemplate;

    public ReplyDBRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void postReply(ReplyWriteDto replyWriteDto) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("REPLY_TABLE").usingGeneratedKeyColumns("ID", "CREATED_AT");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("USERID", replyWriteDto.getUserId());
        parameters.put("ARTICLEID", replyWriteDto.getArticleId());
        parameters.put("CONTENT", replyWriteDto.getContent());

        simpleJdbcInsert.execute(parameters);
    }
}
