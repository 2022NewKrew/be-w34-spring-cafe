package com.kakao.cafe.repository;

import com.kakao.cafe.model.Reply;
import com.kakao.cafe.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReplyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Reply reply){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into replies(article_id, writer, contents, createTime) values ( ?, ?, ?, ? )";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reply.getArticleId());
            ps.setString(2,reply.getUser().getUserId());
            ps.setString(3,reply.getContents());
            ps.setString(4, reply.getCreateTime());
            return ps;
        }, keyHolder);
    }


    public List<Reply> findAll() {
        return jdbcTemplate.query(
                "select * from replies join articles on replies.article_id = articles.article_id" +
                        " join users on replies.writer = users.userId order by id desc",
                mapper
        );
    }

    public Optional<Reply> findOne(Integer id) {
        return jdbcTemplate.query(
                "select * from replies join articles on replies.article_id = articles.article_id" +
                        " join users on replies.writer = users.userId order by id desc",
                mapper,
                id
        ).stream().findAny();
    }


    private final RowMapper<Reply> mapper = (rs, rowNum) -> Reply.builder()
            .id(rs.getInt("id"))
            .articleId(rs.getInt("article_id"))
            .user(User.builder()
                    .user_id(rs.getInt("user_id"))
                    .userId(rs.getString("userID"))
                    .password(rs.getString("password"))
                    .userName(rs.getString("userName"))
                    .email(rs.getString("email"))
                    .build())
            .contents(rs.getString("contents"))
            .createTime(rs.getString("createTime"))
            .build();
}
