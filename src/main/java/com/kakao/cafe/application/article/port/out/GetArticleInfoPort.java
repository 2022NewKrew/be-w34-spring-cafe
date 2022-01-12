package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.domain.article.Article;

public interface GetArticleInfoPort {

    ArticleList getListOfAllArticles();

    Article findArticleByIndex(int index);
}
