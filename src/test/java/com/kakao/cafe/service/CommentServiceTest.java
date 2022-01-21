package com.kakao.cafe.service;

import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.exception.CommentNotFoundException;
import com.kakao.cafe.fake.repository.FakeCommentRepository;
import com.kakao.cafe.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.AccessDeniedException;

class CommentServiceTest {

    private CommentRepository commentRepository;

    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        commentRepository = new FakeCommentRepository();
        commentService = new CommentService(commentRepository);

    }

    @DisplayName("creatComment 테스트 - 코멘트 정보가 주어질 때, 새로운 코멘트가 만들어진다.")
    @Test
    void createComment_CommentInformation_CreateNewComment() {
        // given
        Integer qnaId = 2;
        String writer = "lucas";
        String contents = "test";

        // when
        commentService.createComment(qnaId, writer, contents);

        // then
        Assertions.assertFalse(commentRepository.findById(3).isEmpty());
    }

    @DisplayName("updateComment 테스트 - 코멘트 수정권한이 있는 사람이 수정을 할때, 수정이 제대로 이뤄진다.")
    @Test
    void updateComment_UserIdWhoHasAuthUpdateComment_UpdateComplete() throws AccessDeniedException {
        // given
        Integer commentId = 1;
        String contents = "changedContents";
        String userId = "lucas";

        // when
        commentService.updateComment(commentId, contents, userId);

        // then
        Assertions.assertEquals(contents, commentRepository.findById(commentId).get().getContents());
    }

    @DisplayName("updateComment 테스트 - 존재하지 않는 commentId가 주어질 때, CommentNotFoundException 이 발생")
    @Test
    void updateComment_NotExistCommentId_ThrowAccessDeniedException() {
        // given
        Integer commentId = 3;
        String contents = "changedContents";
        String userId = "lucas";

        // when // then
        Assertions.assertThrows(CommentNotFoundException.class, () -> {
            commentService.updateComment(commentId, contents, userId);
        });
    }

    @DisplayName("updateComment 테스트 - 코멘트 수정권한이 없는 사람이 수정을 할때, AccessDeniedException 이 발생")
    @Test
    void updateComment_UserIdWhoDoNotHaveAuthUpdateComment_ThrowAccessDeniedException() {
        // given
        Integer commentId = 1;
        String contents = "changedContents";
        String userId = "incorrectUserId";

        // when // then
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            commentService.updateComment(commentId, contents, userId);
        });
    }

    @DisplayName("readCommentForUpdate 테스트 - 존재하는 commentId와 수정권한이 있는 userId가 입력되면, 반환되는 ReadCommentForUpdateResponse 가 존재")
    @Test
    void readCommentForUpdate_ExistCommentIdAndValidUserIdForUpdate_ReadCommentForUpdateResponse() throws AccessDeniedException {
        // given
        Integer commentId = 1;
        String userId = "lucas";

        // when
        CommentDto.ReadCommentForUpdateResponse result = commentService.readCommentForUpdate(commentId, userId);

        // then
        Assertions.assertNotNull(result);
    }

    @DisplayName("readCommentForUpdate 테스트 - 존재하지 않는 commentId가 입려되면, CommentNotFoundException 이 발생")
    @Test
    void readCommentForUpdate_NotExistCommentId_ThrowCommentNotFoundException() {
        // given
        Integer commentId = 3;
        String userId = "lucas";

        // when // then
        Assertions.assertThrows(CommentNotFoundException.class, () -> {
            commentService.readCommentForUpdate(commentId, userId);
        });
    }

    @DisplayName("readCommentForUpdate 테스트 - 수정권한이 없는 userId가 입력되면, AccessDeniedException 이 발생")
    @Test
    void readCommentForUpdate_InvalidUserIdForUpdate_ThrowAccessDeniedException() {
        // given
        Integer commentId = 1;
        String userId = "incorrectUserId";

        // when // then
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            commentService.readCommentForUpdate(commentId, userId);
        });
    }

    @DisplayName("deleteComment 테스트 - 존재하는 commentId와 삭제권한이 있는 userId가 주어질 때, 해당 Comment의 deleted가 true")
    @Test
    void deleteComment_ExistCommentIdAndValidUserIdForDelete_CommentGetDeletedIsTrue() throws AccessDeniedException {
        // given
        Integer commentId = 1;
        String userId = "lucas";

        // when
        commentService.deleteComment(commentId, userId);

        // then
        Assertions.assertTrue(commentRepository.findById(commentId).get().getDeleted());
    }

    @DisplayName("deleteComment 테스트 - 존재하지 않는 commentId 가 주어질 때, CommentNotFoundException 발생")
    @Test
    void deleteComment_NotExistCommentId_ThrowCommentNotFoundException() {
        // given
        Integer commentId = 3;
        String userId = "lucas";

        // when // then
        Assertions.assertThrows(CommentNotFoundException.class, () -> {
            commentService.deleteComment(commentId, userId);
        });
    }

    @DisplayName("deleteComment 테스트 - 삭제권한이 없는 userId가 주어질 때, AccessDeniedException 발생")
    @Test
    void deleteComment_InvalidUserIdForDelete_ThrowAccessDeniedException() {
        // given
        Integer commentId = 1;
        String userId = "invalidUserId";

        // when // then
        Assertions.assertThrows(AccessDeniedException.class, () -> {
            commentService.deleteComment(commentId, userId);
        });
    }
}
