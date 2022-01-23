package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ReplyWriteDto;
import com.kakao.cafe.domain.model.Reply;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<Reply> findAllReplies(String articleId) {
        return jdbcTemplate.query("SELECT RT.ID, RT.USERID, RT.ARTICLEID, RT.CONTENT, RT.CREATED_AT, UT.NAME FROM REPLY_TABLE RT JOIN ARTICLE_TABLE AT ON RT.ARTICLEID = AT.ID JOIN USER_TABLE UT on RT.USERID = UT.USERID and ARTICLEID = ? ", replyRowMapper() ,articleId);
    }

    @Override
    public void deleteReply(String id) {
        jdbcTemplate.update("DELETE FROM REPLY_TABLE WHERE ID = ?", id);
    }

    @Override
    public String findUserIdOfReply(String id) {
        return jdbcTemplate.queryForObject("SELECT USERID FROM REPLY_TABLE WHERE ID = ?", String.class, id);
    }

    private RowMapper<Reply> replyRowMapper(){
        return (rs, rowNum) ->
            new Reply(rs.getInt("ID"),
                    rs.getString("USERID"),
                    rs.getString("NAME"),
                    rs.getInt("ARTICLEID"),
                    rs.getString("CONTENT"),
                    rs.getTimestamp("CREATED_AT"));
    }
}
