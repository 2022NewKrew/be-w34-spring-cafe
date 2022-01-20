package com.kakao.cafe.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CommentTest {

    @DisplayName("isValidUpdateUser 테스트 - 업데이트 권한이 있는 userId가 주어질 때, True")
    @Test
    void isValidUpdateUser_ValidUserIdForUpdate_True() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());
        String userId = "lucas";

        // when
        Boolean result = comment.isValidUpdateUser(userId);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("isValidUpdateUser 테스트 - 업데이트 권한이 없는 userId가 주어질 때, False")
    @Test
    void isValidUpdateUser_InvalidUserIdForUpdate_False() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());
        String userId = "invalidUserId";

        // when
        Boolean result = comment.isValidUpdateUser(userId);

        // then
        Assertions.assertFalse(result);
    }

    @DisplayName("isValidDeleteUser 테스트 - 삭제 권한이 있는 userId가 주어질 때, True")
    @Test
    void isValidDeleteUser_ValidUserIdForDelete_True() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());
        String userId = "lucas";

        // when
        Boolean result = comment.isValidDeleteUser(userId);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("isValidDeleteUser 테스트 - 삭제 권한이 없는 userId가 주어질 때, False")
    @Test
    void isValidDeleteUser_InvalidUserIdForDelete_False() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());
        String userId = "invalidUserId";

        // when
        Boolean result = comment.isValidDeleteUser(userId);

        // then
        Assertions.assertFalse(result);
    }

    @DisplayName("delete 테스트 - 호출 시, deleted가 true로 변경")
    @Test
    void delete_CallDelete_ChangeDeletedToTrue() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());

        // when
        comment.delete();

        // then
        Assertions.assertTrue(comment.getDeleted());
    }

    @DisplayName("updateContents 테스트 - 변경된 contents가 주어질 떄, contents가 변경된 contents로 변경")
    @Test
    void updateContents_ChangedContents_ChangeContentsToChangedContents() {
        // given
        Comment comment = new Comment(1, "lucas", "test contents", 1, LocalDateTime.now());
        String changedContents = "changedContents";

        // when
        comment.updateContents(changedContents);

        // then
        Assertions.assertEquals(changedContents, comment.getContents());
    }
}
