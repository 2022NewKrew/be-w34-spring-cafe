package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.repository.article.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void write(Article article) {
        articleRepository.save(article);
    }

    public void update(Article newArticle, Long id) {
        Optional<Article> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        }
        Article updateArticle = article.get();
        updateArticle.setTitle(newArticle.getTitle());
        updateArticle.setContent(newArticle.getContent());
        articleRepository.update(updateArticle);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findArticle(Long id) {
        return articleRepository.findById(id);
    }
}
