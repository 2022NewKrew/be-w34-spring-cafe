package com.kakao.cafe.domain.reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void insert(Reply reply);

    List<Reply> findAll(Long postId);

    Optional<Reply> findById(Long replyId);

    void delete(Long id);
}
