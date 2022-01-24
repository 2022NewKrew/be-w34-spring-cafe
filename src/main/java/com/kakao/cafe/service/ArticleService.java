package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.RepositoryInterface;
import com.kakao.cafe.util.ErrorMessage;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleService {
    private final RepositoryInterface<Article> articleRepository;

    public void join(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    public List<Article> findSubList(int page) {
        return new ArrayList<>(articleRepository.findAll().subList((page - 1) * 10, Math.min(numOfArticles(), page * 10)));
    }

    public Article findOne(Long index) {
        Optional<Article> article = articleRepository.findById(index);
        if (article.isPresent()) {
            return article.get();
        }
        throw new IllegalStateException("not valid index");
    }

    public void checkWriterByLoginUserid(Article article, Long userId) {
        if (!userId.equals(article.getWriterId())) {
            throw new IllegalStateException(ErrorMessage.ARTICLE_FORBIDDEN.getMsg());
        }
    }

    public int numOfArticles() {
        return articleRepository.findAll()
                .size();
    }

    public void updateArticle(Article article) {
        articleRepository.update(article);
    }

    public void deleteArticle(Long index) {
        articleRepository.delete(index);
    }
}
