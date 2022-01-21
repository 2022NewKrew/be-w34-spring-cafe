package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public class GetArticleInfoService implements GetArticleInfoUseCase {

    private final GetArticleInfoPort getArticleInfoPort;

    public GetArticleInfoService(GetArticleInfoPort getArticleInfoPort) {
        this.getArticleInfoPort = getArticleInfoPort;
    }

    @Override
    public ArticleList getListOfAllArticles() {
        return getArticleInfoPort.getListOfAllArticles();
    }

    @Override
    public Article getArticleForUpdate(int id, String userIdViewingForm, UserInfo sessionedUser)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException, UnauthenticatedUserException {
        if (!sessionedUser.getUserId().equals(userIdViewingForm)) {
            throw new UnauthenticatedUserException("인증 오류");
        }
        return getArticleInfoPort.findArticleById(id);
    }

    @Override
    public ArticleDetail getArticleDetail(int id) throws ArticleNotExistException {
        return getArticleInfoPort.findArticleDetailById(id);
    }
}
