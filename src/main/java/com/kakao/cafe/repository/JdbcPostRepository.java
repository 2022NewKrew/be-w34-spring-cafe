package com.kakao.cafe.repository;

import com.kakao.cafe.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
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
        final String sql = "SELECT id, writerId, title, content, createdAt FROM POSTS";
        return jdbcTemplate.query(sql, postRowMapper());
    }

    @Override
    public Optional<Post> findById(UUID id) {
        final String sql = "SELECT id, writerId, title, content, createdAt FROM POSTS WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, postRowMapper(), id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> new Post.Builder(
                rs.getObject("id", UUID.class),
                rs.getObject("writerId", UUID.class),
                rs.getString("title"),
                rs.getObject("content", String.class),
                rs.getTimestamp("createdAt"))
                .build();
    }
}
