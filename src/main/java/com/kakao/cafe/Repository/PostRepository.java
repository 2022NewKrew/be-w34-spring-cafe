package com.kakao.cafe.Repository;

import com.kakao.cafe.model.Post.Post;
import com.kakao.cafe.model.Post.PostResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PostMapper postMapper = new PostMapper();
    private final AtomicLong sequenceId;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String sql = "SELECT COUNT(*) FROM POST";
        Integer numPost = jdbcTemplate.queryForObject(sql, Integer.class);
        if (numPost == null) {
            numPost = 0;
        }
        sequenceId = new AtomicLong(numPost);
    }

    public List<PostResponseDto> getPostList() {
        String sql = "SELECT ID, TITLE, CONTENT FROM POST";

        return jdbcTemplate.query(sql, postMapper);
    }

    public void add(Post post) {
        String sql = "INSERT INTO POST VALUES (?, ?, ?)";

        post.setId(sequenceId.incrementAndGet());

        jdbcTemplate.update(sql,
                post.getId(), post.getTitle(), post.getContent());
    }

    public PostResponseDto findPostById(Long id) {
        String sql = "SELECT ID, TITLE, CONTENT FROM POST WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, postMapper, id);
    }

    private static class PostMapper implements RowMapper<PostResponseDto> {
        @Override
        public PostResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PostResponseDto(
                    rs.getLong("ID"),
                    rs.getString("TITLE"),
                    rs.getString("CONTENT")
            );
        }
    }

}
