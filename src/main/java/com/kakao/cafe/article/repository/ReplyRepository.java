package com.kakao.cafe.article.repository;

import java.util.List;

import com.kakao.cafe.article.entity.ArticleReply;

public interface ArticleReplyRepository {
    void save(ArticleReply reply);

    List<ArticleReply> findAllByArticleId(int articleId);

    void delete(int id);
}
