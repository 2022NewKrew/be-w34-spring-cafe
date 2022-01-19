package com.kakao.cafe.repository.comment;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Comment;

import java.util.List;

public interface CommentRepository {

    Comment edit();

    Comment delete();

    Comment save(Comment comment);

    List<Comment> findAllOfArticle(Article article);
}
