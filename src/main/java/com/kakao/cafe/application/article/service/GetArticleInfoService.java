package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;

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
    public Article getArticleDetail(int id)
        throws ArticleNotExistException, IllegalWriterException, IllegalTitleException, IllegalDateException {
        return getArticleInfoPort.findArticleById(id);
    }
}
