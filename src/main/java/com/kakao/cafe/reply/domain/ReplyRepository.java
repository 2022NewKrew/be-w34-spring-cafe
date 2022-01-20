package com.kakao.cafe.reply.domain;

import java.util.List;

public interface ReplyRepository {

    Long persist(Reply reply);

    List<Reply> findAll(Long articleId);

    void deleteReply(Long id);
}
