package com.kakao.cafe.article.application;

import com.kakao.cafe.article.application.port.in.FindArticleQuery;
import com.kakao.cafe.article.application.port.in.FoundArticleDto;
import com.kakao.cafe.article.application.port.out.LoadArticlePort;
import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindArticleService implements FindArticleQuery {

    private final LoadArticlePort loadArticlePort;

    public FindArticleService(LoadArticlePort loadArticlePort) {
        this.loadArticlePort = loadArticlePort;
    }

    @Override
    public FoundArticleDto find(ArticleId findArticleId) {
        Optional<Article> foundArticle = loadArticlePort.load(findArticleId);
        return foundArticle.map(article -> new FoundArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt()
            ))
            .orElseThrow(NullPointerException::new);
    }

    @Override
    public List<FoundArticleDto> findAll() {
        return loadArticlePort.loadAll().stream().map(article -> new FoundArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt()
            ))
            .collect(Collectors.toUnmodifiableList());
    }
}
