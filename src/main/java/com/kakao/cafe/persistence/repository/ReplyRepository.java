package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.Reply;
import java.util.List;
import java.util.Optional;

public interface ReplyRepository {

    void save(Reply reply);

    void update(Long id, String body);

    void delete(Long id);

    Optional<Reply> findById(Long id);

    List<Reply> findByArticleId(Long articleId);
}
