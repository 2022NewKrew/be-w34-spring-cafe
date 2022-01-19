package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Article;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public void addArticle(Article article, User loginUser) throws IncorrectUserException {
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        articleDao.addArticle(article);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Article getArticle(int index) {
        return articleDao.getArticle(index);
    }

    public void updateArticle(int index, Article article, User loginUser) throws IncorrectUserException {
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), article.getWriter()))
            throw new IncorrectUserException();
        articleDao.updateArticle(index, article);
    }

}
