package com.kakao.cafe.Repository;

import com.kakao.cafe.model.Post.PostCreateRequestDto;
import com.kakao.cafe.model.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDao implements BaseDao<PostResponseDto, Long, PostCreateRequestDto>{
    private final JdbcTemplate jdbcTemplate;
    private final PostMapper postMapper = new PostMapper();

    @Override
    public List<PostResponseDto> findAll() {
        String sql = "SELECT ID, TITLE, CONTENT FROM POST";

        return jdbcTemplate.query(sql, postMapper);
    }

    @Override
    public void save(PostCreateRequestDto post) {
        String sql = "INSERT INTO POST VALUES (?, ?, ?)";

        jdbcTemplate.update(sql,
                post.getId(), post.getTitle(), post.getContent());
    }

    @Override
    public PostResponseDto findById(Long id) {
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
