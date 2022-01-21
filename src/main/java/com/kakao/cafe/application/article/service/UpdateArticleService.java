package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
import com.kakao.cafe.application.article.port.out.UpdateArticlePort;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public class UpdateArticleService implements UpdateArticleUseCase {

    private final UpdateArticlePort updateArticlePort;

    public UpdateArticleService(UpdateArticlePort updateArticlePort) {
        this.updateArticlePort = updateArticlePort;
    }

    @Override
    public void updateArticle(UpdateArticleRequest updateArticleRequest, String userIdUpdatingArticle, UserInfo sessionedUser)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException, UnauthenticatedUserException {
        if (!sessionedUser.getUserId().equals(userIdUpdatingArticle)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        updateArticlePort.updateArticle(updateArticleRequest);
    }
}
