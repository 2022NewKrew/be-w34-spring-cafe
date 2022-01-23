package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository {
    void save(Reply reply);
    void delete(Reply reply);
    List<Reply> findAll(int postid);
    Optional<Reply> findById(int replyid);
}
