package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.util.List;

public interface ReplyRepository {
    List<Reply> findById(String id);

    void addReply(Reply reply);

    void deleteById(String id);
}
