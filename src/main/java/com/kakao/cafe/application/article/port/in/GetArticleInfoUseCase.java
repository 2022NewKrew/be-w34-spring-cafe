package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public interface GetArticleInfoUseCase {

    ArticleList getListOfAllArticles();

    Article getArticleDetail(int id)
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, ArticleNotExistException;
}
