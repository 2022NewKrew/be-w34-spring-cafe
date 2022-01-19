package com.kakao.cafe.domain.post;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    void add(Post post);
    void deleteById(long id);
    Optional<Post> findById(long id);
    List<Post> findAllByWriter(String writer);
    List<Post> findAll();
}
