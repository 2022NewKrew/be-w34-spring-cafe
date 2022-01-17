package com.example.kakaocafe.domain.post;


import com.example.kakaocafe.domain.post.comment.dto.Comment;
import com.example.kakaocafe.domain.post.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final JdbcTemplate jdbcTemplate;

    public int getNumOfComments(long postId, long writerId) {
        final String sql = "SELECT count(distinct (ifnull(c.ID, 0)))  " +
                "FROM POST as p  " +
                "         LEFT OUTER JOIN COMMENT c on p.ID = c.POST_ID  " +
                "WHERE p.ID = ?  " +
                "  AND p.USER_ID = ?  " +
                "  AND p.ISDELETED = false  " +
                "  AND c.ISDELETED = false  ";

        final Integer numOfComments = jdbcTemplate.queryForObject(sql, Integer.class, postId, writerId);
        Objects.requireNonNull(numOfComments);

        return numOfComments;
    }

    public boolean isNotWriter(long postId, long writerId) {
        final String sql = "SELECT NOT EXISTS(SELECT p.id FROM POST as p WHERE p.id=? and p.USER_ID=?)";

        final Boolean isNotExist = jdbcTemplate.queryForObject(sql, Boolean.class, postId, writerId);
        Objects.requireNonNull(isNotExist);

        return isNotExist;
    }

    public long create(WritePostForm writePostForm) {
        final String sql = "INSERT INTO POST (USER_ID, TITLE, CONTENTS) VALUES(?,?,?)";

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});

            ps.setObject(1, writePostForm.getWriterId());
            ps.setObject(2, writePostForm.getTitle());
            ps.setObject(3, writePostForm.getContents());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(UpdatePostForm updatePostForm) {
        final String sql = "UPDATE POST SET TITLE=?, CONTENTS=? WHERE POST.id=?";

        final Object[] params = new Object[]{
                updatePostForm.getTitle(),
                updatePostForm.getContents(),
                updatePostForm.getPostId()
        };

        jdbcTemplate.update(sql, params);
    }

    public List<PostOfTableRow> getAllPostOfTableRow() {
        final String sql = "SELECT p.id, " +
                "p.title, " +
                "p.contents, " +
                "p.view_count, " +
                "FORMATDATETIME(p.created, 'yyyy-MM-dd') as `created`, " +
                "u.name as writer " +
                "FROM POST as p " +
                "JOIN USER as u on p.user_id=u.id " +
                "WHERE p.ISDELETED=false";

        return jdbcTemplate.query(sql, postOfTableRowMapper());
    }

    public void plusViewCount(long id) {
        final String sql = "UPDATE POST SET VIEW_COUNT = VIEW_COUNT+1 WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Post> getPostById(long id) {
        final String sql = "SELECT p.id                                    as `p_id`,  " +
                "       FORMATDATETIME(p.created, 'yyyy-MM-dd hh:mm') as `p_created`,  " +
                "       p.title                                 as `p_title`,  " +
                "       p.contents                              as `p_contents`,  " +
                "       p.view_count                              as `view_count`,  " +
                "       u.name                                  as `p_writer`,  " +
                "       c.id                                    as `c_id`,  " +
                "       cu.name                                 as `c_writer`,  " +
                "       FORMATDATETIME(c.created, 'yyyy-MM-dd hh:mm') as `c_created`,  " +
                "       c.contents                              as `c_contents`  " +
                "FROM POST as p  " +
                "         INNER JOIN USER as u on u.id = p.user_id  " +
                "         LEFT OUTER JOIN COMMENT as c on c.post_id = p.id  " +
                "         LEFT OUTER JOIN USER as cu on c.user_id = cu.id " +
                "WHERE p.id =? " +
                "AND p.ISDELETED=false " +
                "AND ifnull(c.ISDELETED, false) = false";

        final List<PostAndComment> postAndComments = jdbcTemplate.query(sql, postMapper(), id);

        if (postAndComments.isEmpty()) {
            return Optional.empty();
        }

        final List<Comment> comments = postAndComments.stream()
                .filter(this::isNotNullComment)
                .map(Comment::of)
                .collect(Collectors.toList());

        final Post post = Post.of(postAndComments.get(0), comments);

        return Optional.of(post);
    }

    public Optional<PostInfo> getPostInfo(long id) {
        final String sql = "SELECT p.id                                    as `p_id`,  " +
                "       p.title                                 as `p_title`,  " +
                "       p.contents                              as `p_contents` " +
                "FROM POST as p  " +
                "WHERE p.id =? AND p.ISDELETED=false";

        final List<PostInfo> postInfos = jdbcTemplate.query(sql, postInfoMapper(), id);

        return Optional.ofNullable(DataAccessUtils.singleResult(postInfos));
    }

    public void delete(long postId) {
        final String sql = "UPDATE POST SET ISDELETED=true WHERE ID=?";
        jdbcTemplate.update(sql, postId);
    }

    private boolean isNotNullComment(PostAndComment postAndComment) {
        //Long 타입은 DB에서 null 일시 0리턴
        return postAndComment.getCommentId() != 0;
    }

    private RowMapper<PostAndComment> postMapper() {
        return (rs, rowNum) -> {
            final long postId = rs.getLong("p_id");
            final String postCreated = rs.getString("p_created");
            final String postTitle = rs.getString("p_title");
            final String postContents = rs.getString("p_contents");
            final String postWriter = rs.getString("p_writer");
            final long viewCount = rs.getLong("view_count");

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
                    .viewCount(viewCount)
                    .commentId(commentId)
                    .commentCreated(commentCreated)
                    .commentContents(commentContents)
                    .commenter(commenter)
                    .build();
        };
    }

    private RowMapper<PostInfo> postInfoMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String title = rs.getString("title");
            final String contents = rs.getString("contents");

            return new PostInfo(id, title, contents);
        };
    }

    private RowMapper<PostOfTableRow> postOfTableRowMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String writer = rs.getString("writer");
            final String title = rs.getString("title");
            final String created = rs.getString("created");
            final long viewCount = rs.getLong("view_count");

            return new PostOfTableRow(id, writer, title, created, viewCount);
        };
    }
}
