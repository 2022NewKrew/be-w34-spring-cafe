package com.kakao.cafe.application.service;

import com.kakao.cafe.application.dto.ArticleDto;
import com.kakao.cafe.application.dto.CommentDto;

import java.util.List;

public interface BoardService {
    void writeArticle(ArticleDto articleDto);

    CommentDto writeComment(long articleId, CommentDto commentDto);

    List<ArticleDto> findAllArticle();

    ArticleDto findArticleByArticleId(long articleId);

    List<ArticleDto> findArticlesByWriterId(String writerId);

    List<CommentDto> findCommentsByArticleId(long articleId);

    CommentDto findCommentByArticleIdAndCommentId(long articleId, long commentId);

    List<CommentDto> findCommentByWriterId(String writerId);

    void modifyArticle(ArticleDto articleDto);

    void modifyComment(long articleId, CommentDto commentDto);

    void deleteArticle(long articleId);

    void deleteCommentByArticleId(long articleId);

    void deleteComment(long articleId, long commentId);

    boolean isSameArticleWriter(long articleId, String writerId);

    boolean isSameCommentWriter(long articleId, long commentId, String writerId);
}
