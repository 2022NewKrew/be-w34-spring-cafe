package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    Reply save(Reply reply);

    Optional<Reply> findOne(Long id);

    List<Reply> findByArticle(Long articleId);

    List<Reply> findAll();

    void delete(Long id);

}
