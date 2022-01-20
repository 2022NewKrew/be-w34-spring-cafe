package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.reply.Reply;

import java.util.List;

public interface ReplyRepository {
    void insert(Reply reply);
    List<Reply> selectAll(Long articleId);
    void update(Reply reply);
}
