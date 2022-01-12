package com.kakao.cafe.service;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> getPartOfArticles(int pageNumber, int ArticlesPerPage) {
        return articleDao.getArticles(pageNumber, ArticlesPerPage);
    }

    public List<Integer> getPages(int articleLimit) {
        return IntStream
                .rangeClosed(1, Math.floorDiv(articleDao.getSize() - 1, articleLimit) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public void createArticle(String title, String writer, String contents) {
        articleDao.addArticle(title, writer, contents);
    }

    public Article findArticleById(int id) {
        return articleDao.findArticleById(id);
    }
}
