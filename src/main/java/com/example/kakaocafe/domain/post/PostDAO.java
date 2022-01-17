package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.domain.post.comment.CommentDAO;
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

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final JdbcTemplate jdbcTemplate;
    private final CommentDAO commentDAO;

    public int canDelete(long postId, long writerId) {
        final String sql = "SELECT CASE  " +
                "           WHEN isExistPost = false THEN 0  " +
                "           WHEN isExistPost = true AND isExistCommentsOfOtherPeople = true THEN 1  " +
                "           WHEN isExistPost = true AND isExistCommentsOfOtherPeople = false THEN 2  " +
                "           END as result  " +
                "FROM (SELECT (SELECT EXISTS(  " +
                "                             SELECT p.ID  " +
                "                             FROM POST as p  " +
                "                             WHERE p.ID = ?  " +
                "                               AND p.USER_ID = ?  " +
                "                               AND p.ISDELETED = false)) as isExistPost,  " +
                "             (SELECT EXISTS(  " +
                "                             SELECT c.USER_ID  " +
                "                             FROM COMMENT as c  " +
                "                             WHERE c.POST_ID = ?  " +
                "                               AND c.USER_ID != ?  " +
                "                               AND c.ISDELETED = false)) as isExistCommentsOfOtherPeople)";

        final Integer numOfComments = jdbcTemplate.queryForObject(sql, Integer.class, postId, writerId, postId, writerId);

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
                "FORMATDATETIME(p.created, 'yyyy-MM-dd') as created, " +
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
        final String sql = "SELECT p.ID                                          as p_id, " +
                "       FORMATDATETIME(p.created, 'yyyy-MM-dd hh:mm') as p_created, " +
                "       u.NAME                                        as p_writer, " +
                "       p.TITLE                                       as p_title, " +
                "       p.CONTENTS                                    as p_contents, " +
                "       p.VIEW_COUNT                                  as view_count " +
                "FROM POST p " +
                "         inner join USER U on U.ID = p.USER_ID " +
                "WHERE p.ID = ? " +
                "  AND p.ISDELETED = false ";

        final List<Post> posts = jdbcTemplate.query(sql, postMapper(), id);
        final List<Comment> comments = commentDAO.findAllByPostId(id);

        if (posts.isEmpty()) {
            return Optional.empty();
        }

        final Post post = DataAccessUtils.singleResult(posts);
        Objects.requireNonNull(post).setComments(comments);

        return Optional.of(post);
    }

    public Optional<PostInfo> getPostInfo(long id) {
        final String sql = "SELECT p.id                                    as p_id,  " +
                "       p.title                                 as p_title,  " +
                "       p.contents                              as p_contents " +
                "FROM POST as p  " +
                "WHERE p.id =? AND p.ISDELETED=false";

        final List<PostInfo> postInfos = jdbcTemplate.query(sql, postInfoMapper(), id);

        return Optional.ofNullable(DataAccessUtils.singleResult(postInfos));
    }

    public void delete(long postId) {
        final String sql = "UPDATE POST SET ISDELETED=true WHERE ID=?";
        jdbcTemplate.update(sql, postId);
    }

    private RowMapper<Post> postMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("p_id");
            final String created = rs.getString("p_created");
            final String title = rs.getString("p_title");
            final String contents = rs.getString("p_contents");
            final String writer = rs.getString("p_writer");
            final long viewCount = rs.getLong("view_count");

            return new Post(id, writer, title, contents, created, viewCount, new ArrayList<>());
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
