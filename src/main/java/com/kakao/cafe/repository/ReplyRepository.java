package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.util.Optional;

public interface ReplyRepository {
    void save(Reply reply);

    void remove(Reply answer);

    Optional<Reply> findById(int answerId);
}
