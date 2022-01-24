package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository {
    Reply save(Reply reply);

    long delete(long id);

    long deleteByArticleId(long articleId);

    List<Reply> findAllReply(long articleId);

    Optional<String> findUserNicknameById(Long userId);
}
