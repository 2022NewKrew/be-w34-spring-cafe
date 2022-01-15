package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.domain.post.comment.dto.Comment;
import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.PostAndComment;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import com.example.kakaocafe.domain.post.dto.PostOfTableRow;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final JdbcTemplate jdbcTemplate;

    public void create(WritePostForm writePostForm) {
        final String sql = "INSERT INTO POST (USER_ID, TITLE, CONTENTS) VALUES(?,?,?)";

        final Object[] params = {
                writePostForm.getWriterId(),
                writePostForm.getTitle(),
                writePostForm.getContents()
        };

        jdbcTemplate.update(sql, params);
    }

    public List<PostOfTableRow> getAllPostOfTableRow() {
        final String sql = "SELECT p.id, p.title, p.contents, FORMATDATETIME(p.created, 'yyyy-MM-dd') as `created`, u.name as writer " +
                "FROM POST as p " +
                "JOIN USER as u on p.user_id=u.id";

        return jdbcTemplate.query(sql, postOfTableRowMapper());
    }

    public Optional<Post> getPostById(long id) {
        final String sql = "SELECT p.id                                    as `p_id`,  " +
                "       FORMATDATETIME(p.created, 'yyyy-MM-dd hh:mm') as `p_created`,  " +
                "       p.title                                 as `p_title`,  " +
                "       p.contents                              as `p_contents`,  " +
                "       u.name                                  as `p_writer`,  " +
                "       c.id                                    as `c_id`,  " +
                "       cu.name                                 as `c_writer`,  " +
                "       FORMATDATETIME(c.created, 'yyyy-MM-dd hh:mm') as `c_created`,  " +
                "       c.contents                              as `c_contents`  " +
                "FROM POST as p  " +
                "         INNER JOIN USER as u on u.id = p.user_id  " +
                "         LEFT OUTER JOIN COMMENT as c on c.post_id = p.id  " +
                "         INNER JOIN USER as cu on c.user_id = cu.id " +
                "WHERE p.id =?";

        final List<PostAndComment> postAndComments = jdbcTemplate.query(sql, postMapper(), id);

        if (postAndComments.isEmpty()) {
            return Optional.empty();
        }

        final List<Comment> comments = postAndComments.stream()
                .map(PostAndComment::toComment)
                .collect(Collectors.toList());

        final Post post = postAndComments.get(0).toPost(comments);

        return Optional.ofNullable(post);
    }

    private RowMapper<PostAndComment> postMapper() {
        return (rs, rowNum) -> {
            final long postId = rs.getLong("p_id");
            final String postCreated = rs.getString("p_created");
            final String postTitle = rs.getString("p_title");
            final String postContents = rs.getString("p_contents");
            final String postWriter = rs.getString("p_writer");

            final long commentId = rs.getLong("c_id");
            final String commentCreated = rs.getString("c_created");
            final String commentContents = rs.getString("c_contents");
            final String commenter = rs.getString("c_writer");

            return PostAndComment.builder()
                    .postId(postId)
                    .postCreated(postCreated)
                    .postTitle(postTitle)
                    .postContents(postContents)
                    .postWriter(postWriter)
                    .commentId(commentId)
                    .commentCreated(commentCreated)
                    .commentContents(commentContents)
                    .commenter(commenter)
                    .build();
        };
    }

    private RowMapper<PostOfTableRow> postOfTableRowMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String writer = rs.getString("writer");
            final String title = rs.getString("title");
            final String created = rs.getString("created");

            return new PostOfTableRow(id, writer, title, created);
        };
    }
}
