package com.kakao.cafe.repository.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.post.PostResDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    Optional<Post> findByPostId(Long id);
    List<Post> findAll();
}
