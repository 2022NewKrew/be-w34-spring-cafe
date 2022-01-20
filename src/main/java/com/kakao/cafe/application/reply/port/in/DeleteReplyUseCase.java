package com.kakao.cafe.application.reply.port.in;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public interface DeleteReplyUseCase {

    void delete(int id, String userIdDeletingReply, UserInfo sessionedUser) throws UnauthenticatedUserException;

    void deleteAllRepliesInArticle(int articleId, String userIdDeletingReplies, UserInfo sessionedUser)
        throws UnauthenticatedUserException;
}
