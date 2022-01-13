package com.kakao.cafe.domain;

import java.util.List;

public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();

    Post findByPostId(int id);
}
