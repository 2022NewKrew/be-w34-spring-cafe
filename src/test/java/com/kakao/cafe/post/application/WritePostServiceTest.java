package com.kakao.cafe.post.application;

import com.kakao.cafe.post.data.PostsData;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WritePostServiceTest {
    @InjectMocks
    private WritePostService service;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 작성 성공")
    void successWritePost(){
        //given
        Post post = PostsData.getPostList().get(0);

        //when
        service.save(post);

        //then
        verify(postRepository).savePost(eq(post));
    }
}