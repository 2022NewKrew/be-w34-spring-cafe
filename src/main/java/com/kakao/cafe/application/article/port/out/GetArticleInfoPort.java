package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

public interface GetArticleInfoPort {

    ArticleList getListOfAllArticles();

    Article findArticleById(int id)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException;

    ArticleDetail findArticleDetailById(int id) throws ArticleNotExistException;
}
