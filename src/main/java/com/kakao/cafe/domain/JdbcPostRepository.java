package com.kakao.cafe.domain;


import com.kakao.cafe.exceptions.PostNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(JdbcPostRepository.class);

    public JdbcPostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post save(Post post) {
        logger.info("[Jdbc] post save");
        String sql = "insert into post(title, content, user_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getUserId());
        return post;
    }

    @Override
    public List<Post> findAll() {
        logger.info("[Jdbc] post findAll");
        String sql = "select * from post";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Post(rs.getInt("id"),
                rs.getString("user_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("created_at")));
    }

    @Override
    public Post findByPostId(int id) {
        logger.info("[Jdbc] post findByPostId");
        String sql = "select * from post where id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Post(rs.getInt("id"),
                            rs.getString("user_id"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getDate("created_at")),
                    id);
        } catch (EmptyResultDataAccessException e) {
            throw new PostNotFoundException("없는 게시글 입니다");
        }
    }
}
