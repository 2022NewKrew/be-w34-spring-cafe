package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public interface GetArticleInfoUseCase {

    ArticleList getListOfAllArticles();

    Article getArticleForUpdate(int id, String userIdViewingArticle, UserInfo sessionedUser)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, ArticleNotExistException, UnauthenticatedUserException;

    ArticleDetail getArticleDetail(int id) throws ArticleNotExistException;
}
