package com.kakao.cafe.article.repository;

import java.util.List;
import java.util.Optional;

import com.kakao.cafe.article.entity.Reply;

public interface ReplyRepository {
    void save(Reply reply);

    Optional<Reply> findById(int id);

    List<Reply> findAllByArticleId(int articleId);

    void delete(int id);
}
