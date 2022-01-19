package com.kakao.cafe.domain.post.impl;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.NoSuchElementException;
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
        jdbcTemplate.update("INSERT INTO `POST` (writer, title, body) VALUES ( ?, ?, ? )",
                post.getWriter(), post.getTitle(), post.getBody());
    }

    @Override
    public void deleteById(long id) {
        findById(id).ifPresentOrElse(
                x -> jdbcTemplate.update("UPDATE `POST` SET is_removed=true WHERE id=?", id),
                () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    public Optional<Post> findById(long id) {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at, is_removed FROM `POST` WHERE id=? and is_removed=false", postMapper, id)
                .stream().findAny();
    }

    @Override
    public List<Post> findAllByWriter(String writer) {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at, is_removed FROM `POST` WHERE writer=? and is_removed=false", postMapper, writer);
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT id, writer, title, body, created_at, is_removed FROM `POST` WHERE is_removed=false", postMapper);
    }
}
