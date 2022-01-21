package com.kakao.cafe.post.application;

import com.kakao.cafe.post.data.PostsData;
import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCommentServiceTest {
    @InjectMocks
    private DeleteCommentService service;

    @Mock
    private PostRepository repository;

    @Test
    @DisplayName("댓글 삭제하기 성공")
    void successDelete(){
        //given
        final Post post = PostsData.getPostList().get(0);
        final Comment commentDeleted = post.getComments().get(0);

        //when
        service.deleteComment(commentDeleted.getId());

        //then
        verify(repository, times(1))
                .deleteComment(eq(commentDeleted.getId()));
    }
}