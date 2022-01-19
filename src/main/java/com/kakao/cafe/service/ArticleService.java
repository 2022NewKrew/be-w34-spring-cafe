package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.article.ArticleContents;
import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.dto.article.ArticleListShow;
import com.kakao.cafe.dto.article.ArticleModifyCommand;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(ArticleCreateCommand acc) {
        articleRepository.store(acc);
    }

    public void modifyArticle(long articleId, ArticleModifyCommand amc) { articleRepository.modify(articleId, amc); }

    public void deleteArticle(long articleId) { articleRepository.delete(articleId); }

    public ArticleContents getArticle(Long id) {
        Article target = articleRepository.retrieve(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        return new ArticleContents(target);
    }

    public List<ArticleListShow> getAllArticles() {
        return articleRepository.toList().stream()
                .map(ArticleListShow::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
