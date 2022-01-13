package com.kakao.cafe.service.post;

import com.kakao.cafe.dto.post.PostReqDto;
import com.kakao.cafe.dto.post.PostResDto;

import java.util.List;

public interface PostService {
    void addPost(PostReqDto postReqDto);
    List<PostResDto> findPosts();
    PostResDto findPostByPostId(Long postId);
}
