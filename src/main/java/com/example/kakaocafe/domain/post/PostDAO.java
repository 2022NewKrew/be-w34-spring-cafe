package com.example.kakaocafe.domain.post;

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

@Repository
@RequiredArgsConstructor
public class PostDAO {

    private final JdbcTemplate jdbcTemplate;

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
        final String sql = "SELECT NOT EXISTS(SELECT p.ID FROM POST as p WHERE p.ID=? and p.USER_ID=?)";

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
        final String sql = "UPDATE POST SET TITLE=?, CONTENTS=? WHERE POST.ID=?";

        final Object[] params = new Object[]{
                updatePostForm.getTitle(),
                updatePostForm.getContents(),
                updatePostForm.getPostId()
        };

        jdbcTemplate.update(sql, params);
    }

    public List<PostOfTableRow> getAllPostOfTableRow(int offset, int size) {
        final String sql = "SELECT p.ID, " +
                "p.TITLE, " +
                "p.CONTENTS, " +
                "p.VIEW_COUNT, " +
                "DATE_FORMAT(p.CREATED,  '%y-%m-%d') as created, " +
                "u.NAME as writer " +
                "FROM POST as p " +
                "JOIN USER as u on p.USER_ID=u.ID " +
                "WHERE p.ISDELETED=false " +
                "ORDER BY p.CREATED DESC " +
                "LIMIT ? OFFSET ?";

        return jdbcTemplate.query(sql, postOfTableRowMapper(), size, offset);
    }

    public Optional<Integer> numOfPosts(int offset, int limit) {
        final String sql = "SELECT count(temp.t) as cnt " +
                "FROM (select 0 as t " +
                "      FROM POST " +
                "      WHERE ISDELETED = false " +
                "      ORDER BY CREATED DESC " +
                "      LIMIT ? OFFSET ? " +
                "     ) as temp ";

        final List<Integer> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("cnt"), limit, offset);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(DataAccessUtils.singleResult(result));
    }

    public void plusViewCount(long id) {
        final String sql = "UPDATE POST SET VIEW_COUNT = VIEW_COUNT+1 WHERE ID=?";
        jdbcTemplate.update(sql, id);
    }

    public Optional<Post> getPostById(long id) {
        final String sql = "SELECT P.ID                                    as p_id, " +
                "       DATE_FORMAT(P.created,  '%y-%m-%d') as p_created, " +
                "       P.TITLE                                 as p_title, " +
                "       P.CONTENTS                              as p_contents, " +
                "       U.NAME                                  as p_writer, " +
                "       P.VIEW_COUNT                            as view_count, " +
                "       C.ID                                    as c_id, " +
                "       C.CREATED                               as c_created, " +
                "       U2.NAME                                 as c_writer, " +
                "       C.CONTENTS                              as c_contents " +
                "FROM POST P " +
                "         inner join USER U on U.ID = P.USER_ID " +
                "         left outer join COMMENT C on P.ID = C.POST_ID AND C.ISDELETED = false " +
                "         left outer join USER U2 on U2.ID = C.USER_ID " +
                "WHERE P.ID = ? " +
                "  AND P.ISDELETED = false; ";

        final List<PostAndComment> postAndCommentList = jdbcTemplate.query(sql, postAndCommentRowMapper(), id);

        if (postAndCommentList.isEmpty()) {
            return Optional.empty();
        }

        final Post post = Post.of(postAndCommentList);

        return Optional.ofNullable(post);
    }

    public Optional<PostInfo> getPostInfo(long id) {
        final String sql = "SELECT p.ID                                    as p_id,  " +
                "       p.TITLE                                 as p_title,  " +
                "       p.CONTENTS                              as p_contents " +
                "FROM POST as p  " +
                "WHERE p.ID =? AND p.ISDELETED=false";

        final List<PostInfo> postInfos = jdbcTemplate.query(sql, postInfoMapper(), id);

        return Optional.ofNullable(DataAccessUtils.singleResult(postInfos));
    }

    public void delete(long postId) {
        final String sql = "UPDATE POST SET ISDELETED=true WHERE ID=?";
        jdbcTemplate.update(sql, postId);
    }

    private RowMapper<PostAndComment> postAndCommentRowMapper() {
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
            final String commentWriter = rs.getString("c_writer");

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
                    .commentWriter(commentWriter)
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
