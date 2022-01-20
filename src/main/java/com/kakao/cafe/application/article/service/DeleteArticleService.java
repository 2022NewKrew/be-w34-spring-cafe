package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;
import com.kakao.cafe.application.reply.dto.ReplyList;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;

    public DeleteArticleService(DeleteArticlePort deleteArticlePort) {
        this.deleteArticlePort = deleteArticlePort;
    }

    @Override
    public void delete(int id, String userIdDeletingReply, UserInfo sessionedUser, ReplyList replyList)
        throws UnauthenticatedUserException {

        if (!sessionedUser.getUserId().equals(userIdDeletingReply)) {
            throw new UnauthenticatedUserException("게시글을 삭제 할 권한이 없습니다.");
        }

        if (!isPossibleDeleteArticle(replyList, sessionedUser)) {
            throw new UnauthenticatedUserException("댓글을 삭제 할 권한이 없습니다.");
        }
        deleteArticlePort.delete(id);
    }

    private boolean isPossibleDeleteArticle(ReplyList replyList, UserInfo sessionedUser) {
        return replyList.isEmpty() || replyList.containsReplyOf(sessionedUser.getUserId());
    }
}
