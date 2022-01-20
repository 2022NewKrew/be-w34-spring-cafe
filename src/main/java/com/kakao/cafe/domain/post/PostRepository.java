package com.kakao.cafe.domain.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    void save(Post post);
    List<Post> findAll();
    Optional<Post> findById(long id);
    void update(Post post);
    void updateDeletedById(long id);
    void deleteById(long id);
    void deleteAll();
}
