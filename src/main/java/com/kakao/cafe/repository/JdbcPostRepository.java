package com.kakao.cafe.repository;

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
                post.getWriterId(),
                post.getTitle(),
                post.getContent());
    }

    @Override
    public List<Post> findAll() {
        final String sql = "SELECT id, writerId, title, content, createdAt, deleted FROM POSTS WHERE deleted = FALSE";
        return jdbcTemplate.query(sql, postRowMapper);
    }

    @Override
    public Optional<Post> findById(UUID id) {
        final String sql = "SELECT id, writerId, title, content, createdAt, deleted FROM POSTS WHERE id = ? AND deleted = FALSE";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, postRowMapper, id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Post post) {
        final String sql = "UPDATE POSTS SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                post.getTitle(),
                post.getContent(),
                post.getId());
    }

    @Override
    public void delete(Post post) {
        final String sql = "UPDATE POSTS SET deleted = TRUE WHERE id = ?";
        jdbcTemplate.update(sql, post.getId());
    }

    private static RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> new Post.Builder(
                rs.getObject("writerId", UUID.class),
                rs.getString("title"),
                rs.getObject("content", String.class))
                .id(rs.getObject("id", UUID.class))
                .createdAt(rs.getObject("createdAt", LocalDateTime.class))
                .deleted(rs.getBoolean("deleted"))
                .build();
    }
}
