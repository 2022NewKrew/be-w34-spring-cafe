package com.kakao.cafe.application.article.port.out;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.dto.ArticleList;

public interface GetArticleInfoPort {

    ArticleList getListOfAllArticles();

    ArticleDetail findArticleByIndex(int index);
}
