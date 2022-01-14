package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    boolean saveArticle(Article article);
    boolean saveComment(long articleId, Comment comment);

    List<Article> findAllArticle();
    Optional<Article> findArticleByArticleId(long articleId);
    List<Article> findArticlesByWriterId(String writerId);
    List<Comment> findCommentsByArticleId(long articleId);
    Optional<Comment> findComment(long articleId, long commentId);

    boolean modifyArticle(Article article);
    boolean modifyComment(long articleId, Comment comment);

    boolean deleteArticle(long articleId);
    boolean deleteComment(long articleId, long commentId);
}
