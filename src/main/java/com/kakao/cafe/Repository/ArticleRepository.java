package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void saveArticle(Article article);
    Optional<Article> findByArticleId(Long id);
    Optional<Article> findByTitle(String title);
    void saveComment(Comment comment);
    List<Article> findAllArticles();
    List<Comment> findCommentsOf(Long articleId);
}
