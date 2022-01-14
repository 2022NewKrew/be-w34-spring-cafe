package com.kakao.cafe.thread;

import com.kakao.cafe.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class SpringJdbcPostRepository implements PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpringJdbcPostRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long add(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO thread (parent_id, author_id, title, content, status, type) VALUES (?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, post.getParent_id());
            ps.setLong(2, post.getAuthor_id());
            ps.setString(3, post.getTitle());
            ps.setString(4, post.getContent());
            ps.setString(5, post.getStatus());
            ps.setString(6, ThreadType.POST.name());
            return ps;
        }, keyHolder);

        return (Long) keyHolder.getKeys().get("id");
    }

    @Override
    public List<Post> getAll() {
        return jdbcTemplate.query("SELECT * FROM thread WHERE type = ?", postRowMapper(), ThreadType.POST.name());
    }

    @Override
    public Optional<Post> get(Long id) {
        return jdbcTemplate.query("SELECT * FROM thread WHERE id = ? AND type = ?", postRowMapper(), id,
                                  ThreadType.POST.name()).stream().findAny();
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum) -> new Post(rs.getLong("id"), rs.getLong("parent_id"), rs.getLong("author_id"),
                                        rs.getString("title"), rs.getString("content"), rs.getString("status"),
                                        rs.getTimestamp("created_at").toLocalDateTime(),
                                        rs.getTimestamp("last_modified_at").toLocalDateTime());
    }

    @Override
    public void update(Post post) {
        jdbcTemplate.update(
                "UPDATE thread SET parent_id = ?, author_id = ?, title = ?, content = ?, status = ? WHERE id = ? AND type = ?;",
                post.getParent_id(), post.getAuthor_id(), post.getTitle(), post.getContent(), post.getStatus(),
                post.getId(), ThreadType.POST.name());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM thread WHERE id = ? AND type = ?", id, ThreadType.POST.name());
    }
}
