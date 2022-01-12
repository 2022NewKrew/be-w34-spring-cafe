package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import com.kakao.cafe.interfaces.common.UserDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    PostDto add(PostDto postDto);
    Optional<PostDto> findById(long id);
    List<PostDto> findAllByWriter(UserDto user);
    List<PostDto> findAll();
}
