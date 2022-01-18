package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final ErrorService errorService;

    public ArticleService(ArticleDao articleDao, ErrorService errorService) {
        this.articleDao = articleDao;
        this.errorService = errorService;
    }

    public void addArticle(Article article, User loginUser) throws Exception {
        errorService.checkLogin(loginUser);
        errorService.checkSameUser(loginUser.getUserId(), article.getWriter());
        articleDao.addArticle(article);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Article getArticle(int index) {
        return articleDao.getArticle(index);
    }

    public void updateArticle(int index, Article article, User loginUser) throws Exception {
        errorService.checkLogin(loginUser);
        errorService.checkSameUser(loginUser.getUserId(), article.getWriter());
        articleDao.updateArticle(index, article);
    }

}
