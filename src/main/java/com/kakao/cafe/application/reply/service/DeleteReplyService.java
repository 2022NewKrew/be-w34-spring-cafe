package com.kakao.cafe.application.reply.service;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.reply.port.out.DeleteReplyPort;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public class DeleteReplyService implements DeleteReplyUseCase {

    private final DeleteReplyPort deleteReplyPort;

    public DeleteReplyService(DeleteReplyPort deleteReplyPort) {
        this.deleteReplyPort = deleteReplyPort;
    }

    @Override
    public void delete(int id, String userIdDeletingReply, UserInfo sessionedUser) throws UnauthenticatedUserException {
        if (!sessionedUser.getUserId().equals(userIdDeletingReply)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        deleteReplyPort.delete(id);
    }

    @Override
    public void deleteAllRepliesInArticle(int articleId, String userIdDeletingReplies, UserInfo sessionedUser)
        throws UnauthenticatedUserException {
        if (!sessionedUser.getUserId().equals(userIdDeletingReplies)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        deleteReplyPort.deleteAllRepliesInArticle(articleId);
    }
}
