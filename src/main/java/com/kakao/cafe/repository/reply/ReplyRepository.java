package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    List<Reply> findByArticleId(Long articleId);
    Optional<Reply> findById(Long replyId);
    Long insert(Reply reply);
    void delete(Long replyId);
}
