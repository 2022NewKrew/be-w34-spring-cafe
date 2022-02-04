package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DbArticleRepository implements ArticleRepository {
    private final ArticleDao articleDao;


    public DbArticleRepository(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public void create(Article article) {
        articleDao.insert(article);
    }

    @Override
    public List<Article> readAll() {
        return articleDao.selectAll();
    }

    @Override
    public Optional<Article> read(long id) {
        return Optional.ofNullable(articleDao.selectById(id));
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void delete(Long articleId) {
        articleDao.delete(articleId);
    }


}
