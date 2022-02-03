package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleReplyForm;
import com.kakao.cafe.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_ARTICLE;
import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_REPLY;

public class SpringJdbcMemoryReply implements ReplyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SpringJdbcMemoryReply(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Reply save(Reply reply) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("replies").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("articleID", reply.getArticleID());
        parameters.put("author", reply.getAuthor());
        parameters.put("content", reply.getContent());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        reply.setReplyID(key.longValue());

        return reply;
    }

    @Override
    public void delete(Long replyID) {
        jdbcTemplate.update("delete from replies where id=?", replyID);
    }

    @Override
    public Reply findByReplyID(Long replyID){
        List<Reply> result =  jdbcTemplate.query("select * from replies where id = ?",replyRowMapper(), replyID);
        return result.stream().findAny().orElseThrow(() -> new CustomException(NOT_EXIST_REPLY));

    }

    @Override
    public List<Reply> findByArticleID(Long articleID){
        List<Reply> result =  jdbcTemplate.query("select * from replies where articleID = ?",replyRowMapper(), articleID);
        return result;
    }


    private RowMapper<Reply> replyRowMapper(){
        return new RowMapper<Reply>() {
            @Override
            public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long articleID = rs.getLong("articleID");
                String author = rs.getString("author");
                String content = rs.getString("content");
                Reply reply = Reply.add(new SampleReplyForm(articleID, content), author);
                reply.setReplyID(rs.getLong("id"));
                return reply;
            }
        };
    }

}
