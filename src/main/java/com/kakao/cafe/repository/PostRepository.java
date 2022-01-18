package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;
import java.util.List;

public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();

    Post findByPostId(int id);
}
