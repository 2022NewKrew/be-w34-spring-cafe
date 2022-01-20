package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.comment.CommentViewDto;
import com.kakao.cafe.dto.comment.SaveCommentDto;
import com.kakao.cafe.error.exception.nonexist.CommentNotFoundedException;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.testutil.comment.CommentDtoUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment commentInRepo;

    private Post postForComment;

    private User userForComment;

    @BeforeEach
    void setUp() {
        Long userId = Long.valueOf(25);
        Long postId = Long.valueOf(241);
        Long commentId = Long.valueOf(533);

        commentInRepo = Comment
                .builder()
                .id(commentId)
                .contents("good post!")
                .userId(userId)
                .postId(postId)
                .createdAt(OffsetDateTime.now())
                .build();

        postForComment = Post.builder()
                .id(postId)
                .title("Good title")
                .contents("Bad Contents")
                .userId(userId)
                .build();

        userForComment = User.builder()
                .id(userId)
                .email("gallix@kakao.com")
                .password("abcd1234!")
                .nickName("gallix")
                .build();
    }

    @Test
    @DisplayName("User ID, Post ID 로 Comment 찾기 -> 존재하지 않음")
    void findByUserIdAndPostId_nonExist() {
        //Given
        Long userId = Long.valueOf(1);
        Long postId = Long.valueOf(24);
        given(commentRepository.findCommentByUserIdAndPostId(userId, postId)).willReturn(Optional.empty());

        //When, Then
        assertThrows(CommentNotFoundedException.class, () -> commentService.findByUserIdAndPostId(userId, postId));
    }

    @Test
    @DisplayName("User ID, Post ID 로 Comment 찾기 -> 정상")
    void findByUserIdAndPostId() {
        //Given
        Long userId = userForComment.getId();
        Long postId = postForComment.getId();
        given(commentRepository.findCommentByUserIdAndPostId(userId, postId)).willReturn(Optional.of(commentInRepo));

        //When
        Comment result = commentService.findByUserIdAndPostId(userId, postId);

        //Then
        assertEquals(commentInRepo, result);
    }

    @Test
    @DisplayName("게시글의 모든 CommentViewDto List 반환 -> 정상")
    void getCommentViewDtoListByPostId() {
        //Given
        Long postId = Long.valueOf(112);
        List<CommentViewDto> commentViewDtos = Collections.emptyList();
        given(commentRepository.getCommentViewDtoListByPostId(postId)).willReturn(commentViewDtos);

        //When
        List<CommentViewDto> result = commentService.getCommentViewDtoListByPostId(postId);

        //Then
        assertEquals(commentViewDtos, result);
    }

    @Test
    @DisplayName("댓글 추가 -> 정상")
    void addComment() {
        //Given
        Long userId = Long.valueOf(125);
        Long postId = Long.valueOf(46);
        String contents = "gallix is good boy";
        SaveCommentDto saveCommentDto = CommentDtoUtil.createdSaveCommentDto(contents, postId);

        given(commentRepository.save(any(Comment.class))).willAnswer(i -> i.getArguments()[0]);

        //When
        Comment result = commentService.addComment(saveCommentDto, userId);

        //Then
        then(commentRepository).should(times(1)).save(result);
        verifyNewCommentWithSaveCommentDto(saveCommentDto, result);
    }

    private void verifyNewCommentWithSaveCommentDto(SaveCommentDto saveCommentDto, Comment newComment) {
        assertEquals(saveCommentDto.getContents(), newComment.getContents());
        assertEquals(saveCommentDto.getPostId(), newComment.getPostId());
    }

    @Test
    @DisplayName("댓글 수정 -> 존재하지 않음")
    void updateComment_nonExist() {
        //Given
        Long userId = Long.valueOf(125);
        Long postId = Long.valueOf(46);
        SaveCommentDto saveCommentDto = CommentDtoUtil.createdSaveCommentDto(postId);
        given(commentRepository.findCommentByUserIdAndPostId(userId, postId)).willReturn(Optional.empty());

        //When, Then
        assertThrows(CommentNotFoundedException.class, () -> commentService.updateComment(saveCommentDto, userId));
    }

    @Test
    @DisplayName("댓글 수정 -> 정상")
    void updateComment() {
        //Given
        Long userId = Long.valueOf(125);
        Long postId = Long.valueOf(46);
        given(commentRepository.findCommentByUserIdAndPostId(userId, postId)).willReturn(Optional.of(commentInRepo));

        String newContents = "gallix is good boy";
        SaveCommentDto saveCommentDto = CommentDtoUtil.createdSaveCommentDto(newContents, postId);

        given(commentRepository.save(commentInRepo)).willReturn(commentInRepo);

        //When
        Comment result = commentService.updateComment(saveCommentDto, userId);

        //Then
        verifyUpdatedComment(saveCommentDto, result);
    }

    private void verifyUpdatedComment(SaveCommentDto saveCommentDto, Comment comment) {
        assertEquals(saveCommentDto.getContents(), comment.getContents());
    }

    @Test
    @DisplayName("댓글 삭제 -> 존재하지 않음")
    void deleteByCommentIdAndUserId_nonExist() {
        //Given
        Long commentId = Long.valueOf(1254);
        Long userId = Long.valueOf(21);
        given(commentRepository.deleteByCommentIdAndUserId(commentId, userId)).willReturn(0);

        //When, Then
        assertThrows(CommentNotFoundedException.class, () -> commentService.deleteByCommentIdAndUserId(commentId, userId));
    }

    @Test
    @DisplayName("댓글 삭제 -> 정상")
    void deleteByCommentIdAndUserId() {
        //Given
        Long commentId = commentInRepo.getId();
        Long userId = userForComment.getId();
        given(commentRepository.deleteByCommentIdAndUserId(commentId, userId)).willReturn(1);

        //When
        commentService.deleteByCommentIdAndUserId(commentId, userId);

        //Then
        //nothing-to-verify
    }
}
