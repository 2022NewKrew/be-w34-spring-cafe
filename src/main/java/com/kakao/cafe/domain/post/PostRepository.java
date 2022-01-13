package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import com.kakao.cafe.interfaces.common.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    void add(Post post);
    Optional<Post> findById(long id);
    List<Post> findAllByWriter(String writer);
    List<Post> findAll();
}
