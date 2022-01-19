package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(int id);
    Optional<Post> findByTitle(String title);
    Optional<Post> findByWriter(String writer);
    List<Post> findAll();
}
