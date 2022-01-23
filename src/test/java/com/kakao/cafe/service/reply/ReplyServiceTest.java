package com.kakao.cafe.service.reply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kakao.cafe.dao.reply.JdbcReplyStorage;
import com.kakao.cafe.dao.reply.ReplyDao;
import com.kakao.cafe.model.reply.Comment;
import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.reply.ReplyFactory;
import com.kakao.cafe.model.user.UserId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.naming.NoPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReplyServiceTest {

    private ReplyDao replyDao;
    private ReplyService replyService;

    @BeforeEach
    private void before() {
        replyDao = mock(JdbcReplyStorage.class);
        replyService = new ReplyService(replyDao);
    }

    @Test
    void checkLegalPermission() {
        //give
        int replyId = 1;
        String userId = "userId";
        when(replyDao.findReplyById(replyId))
                .thenReturn(Optional.of(
                        ReplyFactory.getReply(1, 1, "userId", "comment", LocalDateTime.now(),
                                false)));
        //when
        //then
        assertThatCode(() -> replyService.checkPermission(replyId, userId))
                .doesNotThrowAnyException();
    }

    @Test
    void checkIllegalPermission() {
        //give
        int replyId = 1;
        String userId = "different";
        when(replyDao.findReplyById(replyId))
                .thenReturn(Optional.of(
                        ReplyFactory.getReply(1, 1, "userId", "comment", LocalDateTime.now(),
                                false)));
        //when
        //then
        assertThatThrownBy(() -> replyService.checkPermission(replyId, userId))
                .isInstanceOf(NoPermissionException.class);
    }
}
