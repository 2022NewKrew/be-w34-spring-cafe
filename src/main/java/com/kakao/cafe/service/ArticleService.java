package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void postArticle(ArticlePostDto article) throws SQLException, NoSuchElementException {
        User user = userRepository.findByName(article.getWriter());

        articleRepository.save(article);
    }

    public List<ArticleDto> getArticleList() {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articleRepository.getAllArticles()) {
            articleDtoList.add(new ArticleDto(article.getId(), article.getWriter(), article.getTitle(), article.getContents()));
        }
        return articleDtoList;
    }

    public ArticleDto findById(int id) throws NoSuchElementException {
        Article article = articleRepository.findById(id);

        return new ArticleDto(
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }
}
