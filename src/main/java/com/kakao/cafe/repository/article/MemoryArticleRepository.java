package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("memoryArticleRepository")
public class MemoryArticleRepository implements ArticleRepository {

    private List<Article> articleList = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Long insertArticle(Article article) {
        article.setId(Long.valueOf(articleList.size()) + 1);
        articleList.add(article);
        return article.getId();
    }

    @Override
    public List<Article> findAll() {
        return List.copyOf(articleList);
    }

    @Override
    public Optional<Article> findById(Long articleId) {
        return articleList.stream()
                .filter(article -> article.getId().equals(articleId))
                .findFirst();
    }
}
