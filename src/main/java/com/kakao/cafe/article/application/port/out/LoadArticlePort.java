package com.kakao.cafe.article.application.port.out;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleId;
import java.util.List;
import java.util.Optional;

public interface LoadArticlePort {

    Optional<Article> load(ArticleId articleId);

    List<Article> loadAll();

}
