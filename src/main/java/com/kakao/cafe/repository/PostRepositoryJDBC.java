package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.domain.Post;
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
import java.time.LocalDate;
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
        try {
            Post post = jdbcTemplate.queryForObject(SQL, new PostMapper(), questionId);
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Post post) {
        this.jdbcTemplate.update("update Post set viewCount=? where id=?",
                post.getViewCount(), post.getId());
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
}
