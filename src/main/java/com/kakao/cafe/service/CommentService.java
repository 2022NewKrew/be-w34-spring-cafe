package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.repository.JdbcTemplatesComment;

import java.util.List;

public class CommentService {
    private JdbcTemplatesComment jdbcTemplatesComment;

    public CommentService(JdbcTemplatesComment jdbcTemplatesComment){
        this.jdbcTemplatesComment = jdbcTemplatesComment;
    }

    public void save(Comment comment){
        jdbcTemplatesComment.save(comment);
    }

    public List<Comment> findAllByArticleId(long articleId){
        return jdbcTemplatesComment.findAllByArticleId(articleId);
    }
}
