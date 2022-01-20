package com.kakao.cafe.article.application.port.in;

import com.kakao.cafe.article.domain.ArticleId;

public interface WriteArticleUseCase {

    ArticleId write(WriteArticleDto writeArticleDto);

}
