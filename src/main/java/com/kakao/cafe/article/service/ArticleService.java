package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void post(Article article) {
        articleRepository.save(article);
    }

    public List<MultipleArticle> getAllArticles() {
        return articleRepository.findAll();
    }

    public SingleArticle getSingleArticle(Long id) {
        if (!articleRepository.increaseViewCount(id)) {
            log.error("조회 수 증가 실패");
        }
        return articleRepository.findSingleArticle(id).orElseThrow(ArticleNotFoundException::new);
    }

    public void update(Long userId, Article article) {
        Article original = getArticle(article.getId());
        original.validate(userId);
        articleRepository.update(article);
    }

    public void delete(Long userId, Long articleId) {
        Article article = getArticle(articleId);
        article.validate(userId);
        articleRepository.delete(article);
    }

    private Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }
}

