package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class JdbcTemplatesComment {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplatesComment(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Comment comment){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO COMMENTS (article_id, writer, contents, created_at) VALUES( ?, ?, ?, ?)";
        jdbcTemplate.update((con) -> {
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1, comment.getArticleId());
            pstmt.setString(2, comment.getWriter());
            pstmt.setString(3, comment.getContents());
            pstmt.setString(4, comment.getTime());
            return pstmt;
        }, keyHolder);
        comment.setId(keyHolder.getKey().longValue());
    }

    public List<Comment> findAllByArticleId(long articleId){
        String sql = "SELECT * FROM COMMENTS WHERE article_id = ?";
        return jdbcTemplate.query(sql, commentRowMapper(), articleId);
    }

    private RowMapper<Comment> commentRowMapper() {
        return (rs, rowNum) -> {
            Comment comment = new Comment(
                    rs.getLong("article_id"),
                    rs.getString("writer"),
                    rs.getString("contents")
            );
            comment.setId(rs.getLong("id"));
            comment.setTime(rs.getString("created_at"));
            return comment;
        };
    }
}
