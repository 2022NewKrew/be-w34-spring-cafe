package com.kakao.cafe.service;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> getPartOfArticles(int pageNumber, int articlesPerPage) {
        return articleDao.getArticles(pageNumber, articlesPerPage);
    }

    public List<Integer> getPages(int articleLimit) {
        return IntStream
                .rangeClosed(1, Math.floorDiv(articleDao.getSize() - 1, articleLimit) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public void createArticle(String title, String writer, String contents) {
        articleDao.addArticle(new Title(title), new Writer(writer), new Contents(contents));
    }

    public Article findArticleById(int id) {
        return findArticle(id);
    }

    private Article findArticle(int id) {
        return articleDao
                .findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시물이 없습니다."));
    }
}
