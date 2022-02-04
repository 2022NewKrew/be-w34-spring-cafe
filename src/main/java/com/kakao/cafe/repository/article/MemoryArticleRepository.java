package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemoryArticleRepository implements ArticleRepository {
    private final ArticleList articleList = ArticleList.getInstance();
    private static Long lastIdNum = 0L;

    @Override
    public void create(Article article) {
        article.setId(++lastIdNum);
        articleList.addArticle(article);
    }

    @Override
    public List<Article> readAll() {
        return articleList.getList();
    }

    @Override
    public Optional<Article> read(long id) {
        return articleList.findById(id);
    }

    @Override
    public void update(Article article) {

    }
}
