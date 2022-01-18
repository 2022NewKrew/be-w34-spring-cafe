package com.kakao.cafe.repository;


import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.rowmapper.PostRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PostRowMapper postRowMapper;

    public Optional<Post> findById(Long id) {
        String sql = "SELECT * FROM `POST` WHERE id = ?";
        Post post = jdbcTemplate.queryForObject(sql, postRowMapper.getPostRowMapper(), id);
        return Optional.ofNullable(post);
    }

    public Optional<PostViewDto> findPostViewDtoById(Long postId) {
        String sql = "SELECT p.id, p.title, p.contents, p.created_at, p.view_num, u.nick_name AS u_nick_name FROM `POST` AS p " +
                "INNER JOIN `USER` AS u " +
                "WHERE p.id = ?";
        PostViewDto postViewDto = jdbcTemplate.queryForObject(sql, postRowMapper.getPostViewDtoRowMapper(), postId);
        return Optional.ofNullable(postViewDto);
    }

    public List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize) {
        int offSet = (pageNum - 1) * pageSize;
        int limit = pageSize;
        String sql = "SELECT p.id, p.title, p.created_at, p.view_num, u.nick_name AS u_nick_name FROM `POST` AS p " +
                "INNER JOIN `USER` AS u " +
                "LIMIT " + limit +
                " OFFSET " + offSet;
        return jdbcTemplate.query(sql, postRowMapper.getSimplePostInfoRowMapper());
    }

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(id) FROM `POST` WHERE id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count > 0;
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            return add(post);
        }
        return update(post);
    }

    private Post add(Post post) {
        String sql = "INSERT INTO `POST` (title, contents, created_at, view_num, user_id) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContents());
            ps.setTimestamp(3, Timestamp.valueOf(post.getCreatedAt().toLocalDateTime()));
            ps.setInt(4, post.getViewNum());
            ps.setLong(5, post.getUserId());
            return ps;
        }, keyHolder);
        post.setId(keyHolder.getKey().longValue());
        return post;
    }

    private Post update(Post post) {
        String sql = "UPDATE `POST` SET title = ?, contents = ?, view_num = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContents(), post.getViewNum(), post.getId());
        return post;
    }

    public void increaseViewNumById(Long postId) {
        String sql = "UPDATE `POST` SET view_num = view_num + 1 WHERE id =?";
        jdbcTemplate.update(sql, postId);
    }

    public int countAll() {
        String sql = "SELECT COUNT(id) FROM `POST`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int deleteByIdAndUserId(Long postId, Long userId) {
        String sql = "DELETE FROM `POST` AS p " +
                "WHERE (p.id = ?) " +
                "AND (p.user_id = ?)";
        return jdbcTemplate.update(sql, postId, userId);
    }
}
