package com.kakao.cafe.model.service;

import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.CommentDto;

import java.util.List;

public interface BoardService {
    void writeArticle(ArticleDto articleDto);
    void writeComment(long articleId, CommentDto commentDto);

    List<ArticleDto> findAllArticle();
    ArticleDto findArticleByArticleId(long articleId);
    List<ArticleDto> findArticlesByWriterId(String writer);
    List<CommentDto> findCommentsByArticleId(long articleId);
    CommentDto findComment(long articleId, long commentId);

    void modifyArticle(ArticleDto articleDto);
    void modifyComment(long articleId, CommentDto commentDto);

    void deleteArticle(long articleId);
    void deleteComment(long articleId, long commentId);
}
