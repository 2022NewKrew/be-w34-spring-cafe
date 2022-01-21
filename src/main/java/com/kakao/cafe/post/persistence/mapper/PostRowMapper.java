package com.kakao.cafe.post.persistence.mapper;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String title = rs.getString("title");
        String content = rs.getString("content");
        String writerName = rs.getString("writerName");
        LocalDateTime timeWritten = rs.getTimestamp("timeWritten").toLocalDateTime();
        boolean isHidden = rs.getString("isHidden").equalsIgnoreCase("true");
        List<Comment> comments = mapComments(rs, id);

        return new Post(id, title, content, writerName, timeWritten, isHidden, comments);
    }

    private List<Comment> mapComments(ResultSet rs, Long postId) throws SQLException{
        if(rs.getString("comment_id") == null){
            return new ArrayList<>();
        }

        List<Comment> comments = new ArrayList<>();
        comments.add(mapComment(rs));
        while(rs.next() && rs.getLong("id") == postId){
            comments.add(mapComment(rs));
        }

        rs.previous();
        return comments;
    }

    private Comment mapComment(ResultSet rs) throws SQLException {
        Long commentId = rs.getLong("comment_id");
        String comment_writerName = rs.getString("comment_writerName");
        String content = rs.getString("comment_content");
        return new Comment(commentId, comment_writerName, content);
    }
}
