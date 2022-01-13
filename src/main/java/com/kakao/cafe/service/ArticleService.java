package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.repository.ArticleDao;
import com.kakao.cafe.repository.UserDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArticleService {
    private final ArticleDao articleDao;
    private final UserDao userDao;

    public ArticleService(ArticleDao articleDao, UserDao userDao) {
        this.articleDao = articleDao;
        this.userDao = userDao;
    }

    public void postArticle(ArticlePostDto article) throws SQLException, NoSuchElementException {
        userDao.findByName(article.getWriter());

        articleDao.save(article.toEntity());
    }

    public List<ArticleDto> getArticleList() {
        List<ArticleDto> articleDtoList = new ArrayList<>();

        for (Article article : articleDao.findAll()) {
            articleDtoList.add(new ArticleDto(article.getId(), article.getWriter(), article.getTitle(), article.getContents()));
        }

        return articleDtoList;
    }

    public ArticleDto findById(int id) throws NoSuchElementException {
        Article article = articleDao.findById(id);

        return new ArticleDto(
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }
}
