package com.kakao.cafe.replies;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Reply save(Reply save);

    Optional<Reply> findById(Long id);

    List<Reply> findAllByArticleIdAndStatus(Long articleId, boolean status);

    void delete(Long id);
}
