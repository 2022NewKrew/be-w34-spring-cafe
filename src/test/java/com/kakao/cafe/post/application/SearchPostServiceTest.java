package com.kakao.cafe.post.application;

import com.kakao.cafe.post.data.PostsData;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SearchPostServiceTest {
    @InjectMocks
    private SearchPostService service;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("모든 게시글 가져오기 성공")
    void successGetAllPosts(){
        //given
        final List<Post> posts = PostsData.getPostList();
        final int page = 1;
        given(postRepository.getPosts(eq(page-1), anyInt())).willReturn(posts);

        //when
        List<Post> actualPosts = service.getPosts(page);

        //then
        assertThat(actualPosts).isEqualTo(posts);
    }

    @Test
    @DisplayName("게시글 한 개 가져오기 실패")
    void failedGetPost(){
        //given
        long postId = 1001L;
        given(postRepository.getPost(postId)).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> service.getPost(postId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없습니다.");
    }

    @ParameterizedTest
    @DisplayName("게시글 한 개 가져오기 성공")
    @MethodSource("com.kakao.cafe.post.data.PostsData#getPostStream")
    void successGetPost(Post post){
        //given
        given(postRepository.getPost(eq(post.getId()))).willReturn(Optional.of(post));

        //when
        Post actualPost = service.getPost(post.getId());

        //then
        assertThat(actualPost).isEqualTo(post);
    }
}