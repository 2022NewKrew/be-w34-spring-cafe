package com.kakao.cafe.qna.comment.repository;

import com.kakao.cafe.qna.comment.Comment;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 6:21
 */
public interface CommentRepository {

    Comment insert(Comment comment);
    Comment update(Comment comment);

    Comment findCommentById(Integer articleId, Integer commentId);
    List<Comment> findCommentsByArticleId(Integer articleId);
}
