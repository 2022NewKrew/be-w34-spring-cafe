package com.kakao.cafe.service.article;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.ArticleFactory;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
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

    public List<ArticleDto> getPartOfArticles(int pageNumber, int articlesPerPage) {
        List<Article> articles = articleDao.getArticles(pageNumber, articlesPerPage);
        return articles
                .stream()
                .map(ArticleDto::new)
                .collect(Collectors.toList());
    }

    public List<Integer> getPages(int articleLimit) {
        return IntStream
                .rangeClosed(1, Math.floorDiv(articleDao.getSize() - 1, articleLimit) + 1)
                .boxed()
                .collect(Collectors.toList());
    }

    public void createArticle(ArticleCreateDto articleCreateDto) {
        articleDao.addArticle(ArticleFactory.getArticle(articleCreateDto));
    }

    public void deleteArticle(int id) {
        articleDao.deleteArticle(id);
    }

    public void updateArticle(int id, ArticleUpdateDto articleUpdateDto) {
        Article article = ArticleFactory.getArticle(id, articleUpdateDto);

        checkExist(id);

        articleDao.updateArticle(article);
    }

    public ArticleDto findArticleById(int id) {
        return new ArticleDto(findArticle(id));
    }

    private Article findArticle(int id) {
        return articleDao
                .findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시물이 없습니다."));
    }

    private void checkExist(int id) {
        if (articleDao.findArticleById(id).isEmpty()) {
            throw new IllegalArgumentException("찾는 게시물이 없습니다.");
        }
    }
}
