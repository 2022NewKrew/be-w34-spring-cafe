package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    boolean saveArticle(Article article);

    Optional<Comment> saveComment(long articleId, Comment comment);

    List<Article> findAllArticle();

    Optional<Article> findArticleByArticleId(long articleId);

    List<Article> findArticlesByWriterId(String writerId);

    List<Comment> findCommentsByArticleId(long articleId);

    Optional<Comment> findCommentByArticleIdAndCommentId(long articleId, long commentId);

    List<Comment> findCommentsByWriterId(String writerId);

    boolean modifyArticle(Article article);

    boolean modifyComment(long articleId, Comment comment);

    boolean deleteArticle(long articleId);

    boolean deleteCommentByArticleId(long articleId);

    boolean deleteComment(long articleId, long commentId);
}
