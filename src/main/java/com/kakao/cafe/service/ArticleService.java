package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleList;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.create(article);
    }

    public int getArticleListSize() {
        return ArticleList.getInstance().size();
    }

    public List<Article> getArticleList() {
        return ArticleList.getInstance().getCopiedArticleList();
    }

    public Article getArticleByIndex(int index) {
        return null;
    }
}
