package com.kakao.cafe.controller.rest;

import com.kakao.cafe.dto.comment.CommentViewDto;
import com.kakao.cafe.dto.comment.SaveCommentDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.testutil.comment.CommentDtoUtil;
import com.kakao.cafe.util.SessionUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CommentRestControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private CommentRestController commentRestController;

    @Test
    @DisplayName("해당 게시글의 모든 댓글에 대한 CommentViewDto 가져오기 -> 정상")
    void getCommentViewDtoListForPost() {
        //Given
        Long postId = Long.valueOf(25);
        List<CommentViewDto> commentViewDtos = Collections.emptyList();
        given(commentService.getCommentViewDtoListByPostId(postId)).willReturn(commentViewDtos);

        //When
        List<CommentViewDto> result = commentRestController.getCommentViewDtoListForPost(postId);

        //Then
        assertEquals(commentViewDtos, result);
    }

    @Test
    @DisplayName("게시글에 댓글 추가 -> 정상")
    void addComment() {
        //Given
        SaveCommentDto saveCommentDto = CommentDtoUtil.createdSaveCommentDto();
        Long loginUserId = Long.valueOf(1);
        stubbingSessionForLoginUserId(loginUserId);

        //When
        commentRestController.addComment(saveCommentDto);

        //Then
        then(commentService).should(times(1)).addComment(saveCommentDto, loginUserId);
    }

    @Test
    @DisplayName("댓글 수정 -> 정상")
    void updateComment() {
        //Given
        SaveCommentDto saveCommentDto = CommentDtoUtil.createdSaveCommentDto();
        Long loginUserId = Long.valueOf(1);
        stubbingSessionForLoginUserId(loginUserId);

        //When
        commentRestController.updateComment(saveCommentDto);

        //Then
        then(commentService).should(times(1)).updateComment(saveCommentDto, loginUserId);
    }

    @Test
    @DisplayName("댓글 삭제 -> 정상")
    void deleteComment() {
        //Given
        Long commentId = Long.valueOf(1);
        Long loginUserId = Long.valueOf(1);
        stubbingSessionForLoginUserId(loginUserId);

        //When
        commentRestController.deleteComment(commentId);

        //Then
        then(commentService).should(times(1)).deleteByCommentIdAndUserId(commentId, loginUserId);
    }

    private void stubbingSessionForLoginUserId(Long loginUserId) {
        given(httpSession.getAttribute(SessionUtil.LOGIN_USER_ID)).willReturn(loginUserId);
    }
}
