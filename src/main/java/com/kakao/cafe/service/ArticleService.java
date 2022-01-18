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
    private static long idIndex = 0;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(String title,String content) {
        articleRepository.create(new Article(idIndex,title,content));
        idIndex++;
    }

    public int getArticleListSize() {
        return articleRepository.getArticleList().size();
    }

    public List<Article> getArticleList() {
        return articleRepository.getArticleList().getCopiedArticleList();
    }

    public Article getArticleByIndex(int id) {
        return articleRepository.findById(id);
    }
}
