package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReplyRepository {
    void save(Reply reply);

    List<Reply> findAllByArticleId(UUID articleId);

    Optional<Reply> findReplyById(UUID replyId);

    void delete(Reply reply);

    void deleteByArticleId(UUID articleId);
}
