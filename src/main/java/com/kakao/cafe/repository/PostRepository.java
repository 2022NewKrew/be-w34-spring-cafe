package com.kakao.cafe.repository;


import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.rowmapper.PostRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * todo : 추후 DB 추가할 때 메소드 구현
 */
@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PostRowMapper postRowMapper;

    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    public Optional<PostViewDto> findPostViewDtoById(Long postId) {
        return Optional.empty();
    }

    public List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize) {
        return Collections.emptyList();
    }

    public boolean existsById(Long aLong) {
        return false;
    }

    public Post save(Post post) {
        return null;
    }

    public void delete(Post post) {

    }

    public int countAll() {
        return 0;
    }
}
