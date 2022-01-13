package com.kakao.cafe.domain.post;

import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.util.mapper.PostMapper;
import org.junit.jupiter.api.Test;

public class PostTest {
    @Test
    void makePostByModel() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        PostMapper.toPost(postDto);
    }
}
