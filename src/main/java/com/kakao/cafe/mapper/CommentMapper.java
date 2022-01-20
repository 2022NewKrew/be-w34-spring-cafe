package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Entity.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int count) throws SQLException {
        int commentId = resultSet.getInt("id");
        int articleId = resultSet.getInt("articleId");
        String userId = resultSet.getString("userId");
        String writer = resultSet.getString("writer");
        String contents = resultSet.getString("contents");
        boolean deleted = resultSet.getBoolean("deleted");
        return new Comment(commentId, articleId, userId, writer, contents, deleted);
    }
}
