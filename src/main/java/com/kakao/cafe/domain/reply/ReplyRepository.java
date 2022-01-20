package com.kakao.cafe.domain.reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void save(Reply reply);
    List<Reply> findAll(Long postId);
    Optional<Reply> findById(Long replyId);
    void remove(Long id);
}
