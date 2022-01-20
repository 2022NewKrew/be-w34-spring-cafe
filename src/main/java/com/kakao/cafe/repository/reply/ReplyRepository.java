package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;

public interface ReplyRepository {
    List<Reply> findByArticleId(Long articleId);
}
