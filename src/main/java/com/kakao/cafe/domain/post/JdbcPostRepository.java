package com.kakao.cafe.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO `POST` (title, content, writer, regDateTime) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getWriter(), post.getRegDateTime());
    }

    @Override
    public List<Post> findAll() {
        String sql = "SELECT id, title, content, writer, regDateTime FROM `POST` WHERE isDeleted=0";
        return jdbcTemplate.query(sql, getPostRowMapper());
    }


    @Override
    public Optional<Post> findById(Long id) {
        String sql = "SELECT id, title, content, writer, regDateTime FROM `POST` WHERE id=? AND isDeleted=0";
        Post post = jdbcTemplate.queryForObject(sql, getPostRowMapper(), id);
        return Optional.ofNullable(post);
    }

    @Override
    public Post edit(Long id, Post post) {
        String sql = "UPDATE `POST` SET title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), id);

        return post;
    }

    @Override
    public void remove(Long id) {
        String sql = "UPDATE `POST` SET isDeleted=1 WHERE id=?";
        jdbcTemplate.update(sql, id);
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
