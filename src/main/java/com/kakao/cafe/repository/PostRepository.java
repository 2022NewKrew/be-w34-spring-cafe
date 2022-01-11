package com.kakao.cafe.repository;

import com.kakao.cafe.model.Post;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {

    Post save(Post post);

    List<Post> findAll();

    Optional<Post> findById(UUID id);
}
