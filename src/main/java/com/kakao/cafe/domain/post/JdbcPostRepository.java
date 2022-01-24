package com.kakao.cafe.domain.post;

import com.kakao.cafe.util.consts.CafeConst;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {


    private final JdbcTemplate jdbcTemplate;

    @Override
    public Post insert(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO `POST` (title, content, writer, regDateTime) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getWriter());
            ps.setTimestamp(4, Timestamp.valueOf(post.getRegDateTime()));
            return ps;
        }, keyHolder);
        post.setId((Objects.requireNonNull(keyHolder.getKey())).longValue());

        return post;
    }

    @Override
    public List<Post> findAll(int pageNumber) {
        String sql = "SELECT id, title, content, writer, regDateTime FROM `POST` WHERE isDeleted=0 ORDER BY regDateTime DESC LIMIT ? OFFSET ?";
        int offset = (pageNumber - 1) * CafeConst.DEFAULT_PAGE_SIZE;
        return jdbcTemplate.query(sql, getPostRowMapper(), CafeConst.DEFAULT_PAGE_SIZE, offset);
    }


    @Override
    public Optional<Post> findById(Long id) {
        String sql = "SELECT id, title, content, writer, regDateTime FROM `POST` WHERE id=? AND isDeleted=0";
        try {
            Post post = jdbcTemplate.queryForObject(sql, getPostRowMapper(), id);
            return Optional.ofNullable(post);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Post update(Long id, Post post) {
        String sql = "UPDATE `POST` SET title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), id);

        return post;
    }

    @Override
    public void remove(Long id) {
        String sql = "UPDATE `POST` SET isDeleted=1 WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(id) AS count FROM `POST` WHERE isDeleted=0";
        try {
            Integer count = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("count"));
            return (count == null) ? 0 : count;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }

    }

    private RowMapper<Post> getPostRowMapper() {
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setWriter(rs.getString("writer"));
            post.setRegDateTime(rs.getTimestamp("regDateTime").toLocalDateTime());

            return post;
        };
    }

}
