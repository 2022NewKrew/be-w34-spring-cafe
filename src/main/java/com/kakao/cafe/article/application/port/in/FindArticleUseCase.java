package com.kakao.cafe.article.application.port.in;

import com.kakao.cafe.article.domain.ArticleId;
import java.util.List;

public interface FindArticleUseCase {

    FoundArticleDto find(ArticleId findArticleId);

    List<FoundArticleDto> findAll();
}
