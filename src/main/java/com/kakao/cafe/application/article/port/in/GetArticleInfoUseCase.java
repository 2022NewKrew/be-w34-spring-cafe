package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;

public interface GetArticleInfoUseCase {

    ArticleList getListOfAllArticles();

    ArticleDetail getArticleDetail(int index);
}
