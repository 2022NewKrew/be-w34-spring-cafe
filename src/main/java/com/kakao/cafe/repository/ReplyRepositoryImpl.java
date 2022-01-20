package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Reply;
import com.kakao.cafe.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class ReplyRepositoryImpl implements ReplyRepository{
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ReplyRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Reply save(Reply reply) {
        String sql = "INSERT INTO REPLY (WRITER, CREATED_TIME, CONTENT, ARTICLE_ID) "+
                "VALUES (:writer, :createdTime, :content, :articleId)";

        Map<String, Object> param = new HashMap<>();
        param.put("writer", reply.getUser().getId());
        param.put("createdTime", reply.getCreatedTime());
        param.put("content", reply.getContent());
        param.put("articleId", reply.getArticleId());

        this.namedParameterJdbcTemplate.update(sql, param);

        return reply;
    }

    @Override
    public List<Reply> findByArticleId(String articleId) {
        String sql = "SELECT U.USER_ID AS USER_ID, " +
                "U.PASSWORD AS PASSWORD, " +
                "U.NAME AS NAME, " +
                "U.EMAIL AS EMAIL, " +
                "U.CREATED_TIME AS USER_CREATED_TIME, "+
                "R.CREATED_TIME AS CREATED_TIME, " +
                "R.CONTENT AS CONTENT, " +
                "R.ARTICLE_ID AS ARTICLE_ID " +
                "FROM REPLY AS R "+
                "JOIN WRITER AS U "+
                "ON U.USER_ID = R.WRITER " +
                "WHERE ARTICLE_ID = :articleId";

        Map<String, Object> param = new HashMap<>();
        param.put("articleId", articleId);
        return namedParameterJdbcTemplate.query(sql, param, replyRowMapper());
    }

    private RowMapper<Reply> replyRowMapper() {
        return (rs, rowNum) -> new Reply(
                new User(
                        rs.getInt("USER_ID"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getDate("USER_CREATED_TIME").toLocalDate()
                ),
                rs.getTimestamp("CREATED_TIME").toLocalDateTime(),
                rs.getString("CONTENT"),
                rs.getInt("ARTICLE_ID")
        );
    }
}
