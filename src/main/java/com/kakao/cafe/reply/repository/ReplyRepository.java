package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    void save(Reply reply);

    Optional<Reply> findById(Long id);

    List<Reply> findByArticleId(Long articlePK);

    List<Reply> findAll();

    void delete(Long id);
}
