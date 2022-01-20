package com.kakao.cafe.reply.domain;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    Long persist(Reply reply);

    List<Reply> findAll(Long articleId);

    void deleteReply(Long id);

    Optional<Reply> find(Long id);
}
