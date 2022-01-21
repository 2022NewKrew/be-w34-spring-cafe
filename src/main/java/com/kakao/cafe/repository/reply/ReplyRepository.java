package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    void save(Reply reply);

    List<Reply> findByArticleId(Long articleId);

    void deleteById(Long id);

    Optional<Reply> findById(Long id);
}
