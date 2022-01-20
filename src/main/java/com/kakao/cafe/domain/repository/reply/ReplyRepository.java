package com.kakao.cafe.domain.repository.reply;

import com.kakao.cafe.domain.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    void save(Reply reply);

    Optional<Reply> findById(Long id);

    List<Reply> findAllByArticleId(Long articleId);

    void delete(Long id);
}
