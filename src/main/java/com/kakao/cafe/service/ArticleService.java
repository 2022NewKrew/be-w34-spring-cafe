package com.kakao.cafe.service;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.dto.ArticleCreateDto;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ArticleService {
    private static int nextArticleId = 1;

    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<ArticleDto> getPartOfArticles(int firstIndex, int finalIndex) {
        List<Article> articles = articleDao.getPartOfArticles(firstIndex, finalIndex);
        return articles
                .stream()
                .map(article -> new ArticleDto(article.getId(), article.getTitle(), article.getWriter(), article.getContents(), article.getCreateDate()))
                .collect(Collectors.toList());
    }

    public List<Integer> getPages(int articleLimit) {
        return IntStream
                .rangeClosed(1, Math.floorDiv(articleDao.getSize() - 1, articleLimit) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public void createArticle(ArticleCreateDto articleCreateDto) {
        articleDao.add(new Article(nextArticleId, articleCreateDto.getTitle(), articleCreateDto.getWriter(), articleCreateDto.getContents()));
        nextArticleId++;
    }

    public Article findArticleById(int id) {
        return articleDao.get(id);
    }
}
