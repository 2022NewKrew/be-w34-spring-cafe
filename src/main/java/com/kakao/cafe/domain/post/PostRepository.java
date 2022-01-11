package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post add(Post post);
    Optional<Post> findById(long id);
    List<Post> findAllByWriter(User user);
    List<Post> findAll();
}
