package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceV1 implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> inquireAllArticles() {
        return articleRepository.findAllArticle();
    }

    @Override
    public void postArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    @Transactional(readOnly = true)
    public Article inquireOneArticle(Long articleId) {
        Article article = articleRepository.findArticle(articleId);
        article.setComments(new Comments(commentRepository.findAllOfArticle(article)));
        return article;
    }

    @Override
    public void editArticle(Long articleId, Article article) {

    }

    @Override
    public void deleteArticle(Long articleId) {

    }

    @Override
    public void deleteAllArticles() {

    }
}
