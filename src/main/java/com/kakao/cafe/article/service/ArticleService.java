package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import com.kakao.cafe.article.exception.ArticleNotFoundException;
import java.util.List;
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

    public SingleArticle getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
    }
}
