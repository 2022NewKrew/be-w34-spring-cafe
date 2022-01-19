package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("postRepositoryJDBC")
public class PostRepositoryJDBC implements PostRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public PostRepositoryJDBC(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Post").usingGeneratedKeyColumns("id");
    }

    @Override
    public int save(Post post) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", post.getTitle())
                .addValue("content", post.getContent())
                .addValue("createdAt", post.getCreatedAt())
                .addValue("viewCount", post.getViewCount())
                .addValue("writerId", post.getWriter().getId());

        Number id = simpleJdbcInsert.executeAndReturnKey(params);

        return id.intValue();
    }

    @Override
    public Optional<List<Post>> findAll() {
        String SQL = "select m.id as mId," +
                " m.userId as mUserId," +
                " m.password as mPassword," +
                " m.email as mEmail," +
                " m.name as mName," +
                " m.createdAt as mCreatedAt," +
                " p.id as pId," +
                " p.title as pTitle," +
                " p.content as pContent," +
                " p.createdAt as pCreatedAt," +
                " p.viewCount as pViewCount from Post p " +
                "INNER JOIN Member m on m.id = p.writerId";
        List<Post> posts = jdbcTemplate.query(SQL, new PostMapper());
        return Optional.of(posts);
    }

    @Override
    public Optional<Post> findById(int questionId) {
        String SQL = "select m.id as mId," +
                " m.userId as mUserId," +
                " m.password as mPassword," +
                " m.email as mEmail," +
                " m.name as mName," +
                " m.createdAt as mCreatedAt," +
                " p.id as pId," +
                " p.title as pTitle," +
                " p.content as pContent," +
                " p.createdAt as pCreatedAt," +
                " p.viewCount as pViewCount from Post p " +
                "INNER JOIN Member m on m.id = p.writerId " +
                "WHERE p.id = ?";

        String ReplySQL = "select r.id as rId, " +
                "r.content as rContent, " +
                "r.createdAt as rCreatedAt, " +
                "m.id as mId, " +
                "m.userId as mUserId, " +
                "m.password as mPassword, " +
                "m.email as mEmail, " +
                "m.name as mName, " +
                "m.createdAt as mCreatedAt, " +
                "p.id as pId, " +
                "p.title as pTitle, " +
                "p.content as pContent, " +
                "p.createdAt as pCreatedAt, " +
                "p.viewCount as pViewCount " +
                "FROM Post p " +
                "INNER JOIN Reply r on r.postId = p.id " +
                "INNER JOIN Member m on m.id = r.writerId " +
                "WHERE p.id = ? and r.isRemoved = false";

        try {
            Post post = jdbcTemplate.queryForObject(SQL, new PostMapper(), questionId);
            if (post != null) {
                List<Reply> replyList = jdbcTemplate.query(ReplySQL, new ReplyMapper(), questionId);
                post.setReplyList(replyList);
            }
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Post post) {
        this.jdbcTemplate.update("update Post set " +
                        "viewCount=?, " +
                        "title=?, " +
                        "content=? " +
                        "where id=?",
                post.getViewCount(),
                post.getTitle(),
                post.getContent(),
                post.getId());
    }

    @Override
    public void remove(Post post) {
        this.jdbcTemplate.update("delete Post where id=?", post.getId());
    }

    private static class PostMapper implements RowMapper<Post> {
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getInt("mId"),
                    rs.getString("mUserId"),
                    rs.getString("mPassword"),
                    rs.getString("mEmail"),
                    rs.getString("mName"),
                    rs.getDate("mCreatedAt").toLocalDate()
            );

            return new Post(
                    rs.getInt("pId"),
                    rs.getString("pTitle"),
                    rs.getString("pContent"),
                    rs.getDate("pCreatedAt").toLocalDate(),
                    member,
                    rs.getInt("pViewCount")
            );
        }
    }

    private static class ReplyMapper implements RowMapper<Reply> {
        public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
                    rs.getInt("mId"),
                    rs.getString("mUserId"),
                    rs.getString("mPassword"),
                    rs.getString("mEmail"),
                    rs.getString("mName"),
                    rs.getDate("mCreatedAt").toLocalDate()
            );

            Post post = new Post(
                    rs.getInt("pId"),
                    rs.getString("pTitle"),
                    rs.getString("pContent"),
                    rs.getDate("pCreatedAt").toLocalDate(),
                    member,
                    rs.getInt("pViewCount")
            );

            return new Reply(
                    rs.getInt("rId"),
                    post,
                    member,
                    rs.getString("rContent"),
                    Instant.ofEpochMilli(rs.getDate("rCreatedAt").getTime())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime()
            );
        }
    }
}
