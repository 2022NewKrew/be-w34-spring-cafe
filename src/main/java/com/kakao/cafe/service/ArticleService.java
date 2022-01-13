package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.repository.ArticleRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void postArticle(ArticlePostDto article) throws SQLException {
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
        if (article == null)
            throw new NoSuchElementException("해당 id를 가진 article이 존재하지 않음");

        return new ArticleDto(
                article.getId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }
}
