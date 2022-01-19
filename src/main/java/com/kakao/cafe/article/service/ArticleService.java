package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.common.exception.ForbiddenException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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
        articleRepository.increaseViewCount(id);
        return articleRepository.findSingleArticle(id).orElseThrow(ArticleNotFoundException::new);
    }

    public void update(Long userId, Article article) {
        Article original = getArticle(article.getId());
        validateAuthor(userId, original);
        articleRepository.update(article);
    }

    public void delete(Long authorId, Long articleId) {
        Article article = getArticle(articleId);
        validateAuthor(authorId, article);
        articleRepository.delete(article);
    }

    private void validateAuthor(Long authorId, Article article) {
        if (!article.isAuthor(authorId)) {
            throw new ForbiddenException();
        }
    }

    private Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }
}
