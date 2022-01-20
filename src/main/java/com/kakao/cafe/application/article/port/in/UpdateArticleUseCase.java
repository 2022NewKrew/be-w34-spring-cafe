package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public interface UpdateArticleUseCase {

    void updateArticle(UpdateArticleRequest updateArticleRequest, String userIdUpdatingArticle, UserInfo sessionedUser)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException, UnauthenticatedUserException;
}
