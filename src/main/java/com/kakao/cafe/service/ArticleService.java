package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.article.*;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public void createArticle(ArticleCreateCommand acc) {
        articleRepository.store(acc);
    }

    public void modifyArticle(long articleId, ArticleModifyCommand amc) { articleRepository.modify(articleId, amc); }

    public void deleteArticle(long articleId) { articleRepository.delete(articleId); }

    public ArticleContents getArticle(Long id) {
        Article article = articleRepository.retrieve(id)
                .orElseThrow(() -> new NoSuchElementException("Article not found"));
        ArticleWithWriterName target = new ArticleWithWriterName(article, userRepository);
        return new ArticleContents(target);
    }

    public List<ArticleListShow> getAllArticles() {
        return articleRepository.toList().stream()
                .map(article -> new ArticleListShow(new ArticleWithWriterName(article, userRepository)))
                .collect(Collectors.toUnmodifiableList());
    }
}
