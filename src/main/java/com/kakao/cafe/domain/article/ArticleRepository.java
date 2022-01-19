package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.dto.ArticleUpdateForm;

import java.util.List;

public interface ArticleRepository {
    public Long save(ArticleSaveForm article);
    public List<Article> findAll();
    public Article findById(Long postId);
    public void delete(Long id);
    public boolean checkAuthor(Long id, Long userId);
    public void update(Long id, ArticleUpdateForm updateForm);
    public void incrementNumOfComment(Long articleId);
}
