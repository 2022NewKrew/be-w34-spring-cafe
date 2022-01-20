package com.kakao.cafe.qna.comment;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-19 019
 * Time: 오후 6:21
 */
public interface CommentRepository {

    Comment insert(Comment comment);
    Comment update(Comment comment);
    List<Comment> findCommentsByArticleId(Integer articleId);
}
