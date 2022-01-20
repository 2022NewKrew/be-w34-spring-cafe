package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    List<Reply> findAll();
    List<Reply> findByArticleId(int articleId);
    Optional<Reply> findById(int id);
    void save(Reply reply);
    void update(Reply reply);
    void delete(int id);
}
