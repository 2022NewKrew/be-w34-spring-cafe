package com.kakao.cafe.article.application.port.out;

import com.kakao.cafe.article.domain.ArticleId;

public interface SaveArticlePort {

    ArticleId save(CreateArticleDto createArticleDto);

}
