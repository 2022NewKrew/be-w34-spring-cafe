package com.kakao.cafe.thread.repository;

import com.kakao.cafe.thread.domain.Post;
import com.kakao.cafe.thread.domain.ThreadType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

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
            // Post's parentId is set to null explicitly
            ps.setNull(1, NULL);
            ps.setLong(2, post.getAuthorId());
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
        return (rs, rowNum) -> Post.builder()
                                   .id(rs.getLong("id"))
                                   .parentId(rs.getLong("parent_id"))
                                   .authorId(rs.getLong("author_id"))
                                   .title(rs.getString("title"))
                                   .content(rs.getString("content"))
                                   .status(rs.getString("status"))
                                   .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                                   .lastModifiedAt(rs.getTimestamp("last_modified_at").toLocalDateTime())
                                   .build();
    }

    @Override
    public void update(Post post) {
        jdbcTemplate.update(
                "UPDATE thread SET parent_id = ?, author_id = ?, title = ?, content = ?, status = ? WHERE id = ? AND type = ?;",
                post.getParentId(), post.getAuthorId(), post.getTitle(), post.getContent(), post.getStatus(),
                post.getId(), ThreadType.POST.name());
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update("DELETE FROM thread WHERE id = ? AND type = ?", id, ThreadType.POST.name());
    }
}
