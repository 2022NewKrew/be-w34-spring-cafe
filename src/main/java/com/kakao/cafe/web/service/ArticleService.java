package com.kakao.cafe.web.service;

import com.kakao.cafe.web.domain.Article;
import com.kakao.cafe.web.exception.NotFoundException;
import com.kakao.cafe.web.repository.article.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
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

    @Transactional
    public void update(Article newArticle, Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));

        article.setTitle(newArticle.getTitle());
        article.setContent(newArticle.getContent());
        articleRepository.update(article);
    }

    @Transactional
    public void delete(Long id) {
        articleRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));
        articleRepository.delete(id);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public Article findArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("존재하지 않는 게시글입니다."));
    }
}
