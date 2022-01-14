package com.kakao.cafe.domain.post.impl;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class JdbcTemplatePostRepository implements PostRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final PostMapper postMapper;

    public JdbcTemplatePostRepository(DataSource dataSource, PostMapper postMapper) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.postMapper = postMapper;
    }

    @Override
    public void add(Post post) {
        jdbcTemplate.update("INSERT INTO `POST` (writer, title, body, created_at) VALUES ( ?, ?, ?, ? )",
                post.getWriter(), post.getTitle(), post.getBody(), Timestamp.valueOf(post.getCreatedAt()));
    }

    @Override
    public Optional<Post> findById(long id) {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at FROM `POST` WHERE id=?", postMapper, id)
                .stream().findAny();
    }

    @Override
    public List<Post> findAllByWriter(String writer) {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at FROM `POST` WHERE writer=?", postMapper, writer);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at FROM `POST`", postMapper);
    }
}
