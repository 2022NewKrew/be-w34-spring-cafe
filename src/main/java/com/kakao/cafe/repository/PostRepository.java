package com.kakao.cafe.repository;


import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * todo : 추후 DB 추가할 때 메소드 구현
 */
@org.springframework.stereotype.Repository
public class PostRepository implements Repository<Post, Long>{
    @Override
    public Optional<Post> findById(Long aLong) {
        return Optional.empty();
    }

    public Optional<PostViewDto> findPostViewDtoById(Long postId) {
        return Optional.empty();
    }

    public List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize) {
        return Collections.emptyList();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void delete(Post post) {

    }

    @Override
    public int countAll() {
        return 0;
    }
}
