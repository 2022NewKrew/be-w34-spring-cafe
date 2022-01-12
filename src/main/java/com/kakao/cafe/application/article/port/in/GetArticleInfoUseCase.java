package com.kakao.cafe.application.article.port.in;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.domain.article.Article;

public interface GetArticleInfoUseCase {

    ArticleList getListOfAllArticles();

    Article getArticleDetail(int index);
}
