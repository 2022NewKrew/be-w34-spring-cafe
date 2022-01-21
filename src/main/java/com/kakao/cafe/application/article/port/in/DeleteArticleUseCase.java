package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public interface DeleteArticleUseCase {

    void delete(int id, String userIdDeletingReply, UserInfo sessionedUser)
        throws UnauthenticatedUserException, ArticleNotExistException;
}
