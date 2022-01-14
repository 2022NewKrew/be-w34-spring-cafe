package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;

public interface GetArticleInfoPort {

    ArticleList getListOfAllArticles();

    Article findArticleById(int index) throws ArticleNotExistException;
}
