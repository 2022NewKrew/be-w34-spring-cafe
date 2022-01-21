package com.kakao.cafe.repository.post;

import com.kakao.cafe.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final RowMapper<Post> postRowMapper = postRowMapper();

    public JdbcPostRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Post post) {
        final String sql = "INSERT INTO POSTS (writerId, title, content) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                post.getWriterId().toString(),
                post.getTitle(),
                post.getContent());
    }

    @Override
    public List<Post> findAll() {
        final String sql = "SELECT id, writerId, title, content, createdAt, updatedAt, deletedAt, deleted FROM POSTS WHERE deleted = FALSE";
        return jdbcTemplate.query(sql, postRowMapper);
    }

    @Override
    public Optional<Post> findById(UUID id) {
        final String sql = "SELECT id, writerId, title, content, createdAt, updatedAt, deletedAt, deleted FROM POSTS WHERE id = ? AND deleted = FALSE";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, postRowMapper, id.toString()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Post post) {
        final String sql = "UPDATE POSTS SET title = ?, content = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql,
                post.getTitle(),
                post.getContent(),
                post.getId().toString());
    }

    @Override
    public void delete(Post post) {
        final String sql = "UPDATE POSTS SET deleted = TRUE, deletedAt = CURRENT_TIMESTAMP WHERE id = ?";
        jdbcTemplate.update(sql, post.getId().toString());
    }

    private static RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> new Post.Builder(
                UUID.fromString(rs.getString("writerId")),
                rs.getString("title"),
                rs.getObject("content", String.class))
                .id(UUID.fromString(rs.getString("id")))
                .createdAt(rs.getObject("createdAt", LocalDateTime.class))
                .updatedAt(rs.getObject("updatedAt", LocalDateTime.class))
                .deletedAt(rs.getObject("deletedAt", LocalDateTime.class))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }
}
