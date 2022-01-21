package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.reply.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void insert(Reply reply);
    List<Reply> selectAll(Long articleId);
    Optional<Reply> selectByReplyId(Long replyId);
    void update(Reply reply);

}
