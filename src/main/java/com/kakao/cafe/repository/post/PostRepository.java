package com.kakao.cafe.repository.post;

import com.kakao.cafe.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    void save(Post post);

    List<Post> findAll();

    Optional<Post> findById(UUID id);

    void update(Post post);

    void delete(Post post);
}
