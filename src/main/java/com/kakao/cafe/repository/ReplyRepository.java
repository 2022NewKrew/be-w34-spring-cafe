package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void save(ReplyRequestDTO reply);
    Optional<Reply> findById(Long id);
    List<Reply> findByArticle(Long id);
    void delete(Long id);
    void deleteAll(Long id);
}
