package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateReplyRepository implements ReplyRepository{

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplateReplyRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Reply reply) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("reply").usingGeneratedKeyColumns("replyid");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", reply.getWriter());
        parameters.put("postid", reply.getPostid());
        parameters.put("createddate", reply.getCreateddate());
        parameters.put("content", reply.getContent());
        parameters.put("userid",reply.getUserid());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        reply.setId(key.intValue());
    }

    @Override
    public void delete(Reply reply) {
        String query = "delete from reply where id = ?";
        jdbcTemplate.update(query,reply.getId());
    }

    @Override
    public List<Reply> findAll(int postid) {
        return jdbcTemplate.query("select * from reply", replyRowMapper());
    }

    @Override
    public Optional<Reply> findById(int replyid) {
        List<Reply> result = jdbcTemplate.query("select * from reply where replyid = ?", replyRowMapper(), replyid);
        return result.stream().findAny();
    }

    private RowMapper<Reply> replyRowMapper(){
        return (rs, rowNum) -> {
            Reply reply = new Reply();
            reply.setId(rs.getInt("replyid"));
            reply.setContent(rs.getString("content"));
            reply.setWriter(rs.getString("writer"));
            reply.setPostid(rs.getInt("postid"));
            reply.setUserid(rs.getInt("userid"));
            reply.setCreateddate(rs.getDate("createddate").toLocalDate());
            return reply;
        };
    }
}
