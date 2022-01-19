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

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletePostServiceTest {
    @InjectMocks
    private DeletePostService service;

    @Mock
    private PostRepository repository;

    @Test
    @DisplayName("삭제하기 성공")
    void successDelete(){
        //given
        final Long postId = 1001L;
        final Post post = PostsData.getPostList().get(0);
        given(repository.getPost(eq(postId))).willReturn(Optional.of(post));

        //when
        service.softDelete(postId, post.getWriterName());

        //then
        verify(repository, times(1)).softDelete(eq(postId));
    }

    @Test
    @DisplayName("게시글이 존재하지 않아서 삭제 실패")
    void failedDeleteWhenDoesNotExist(){
        //given
        final Long postId = 1001L;
        final String writerName = PostsData.getPostList().get(0).getWriterName();
        given(repository.getPost(eq(postId))).willReturn(Optional.empty());

        //when
        //then
        assertThatThrownBy(() -> service.softDelete(postId, writerName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없습니다.");
    }

    @Test
    @DisplayName("작성자가 아니라서 삭제 실패")
    void failedDeleteWhenNotWriter(){
        //given
        final Long postId = 1001L;
        final Post post = PostsData.getPostList().get(0);
        final String wrongWriterName = post.getWriterName().concat("a");
        given(repository.getPost(eq(postId))).willReturn(Optional.of(post));

        //when
        //then
        assertThatThrownBy(() -> service.softDelete(postId, wrongWriterName))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("작성자");
    }
}