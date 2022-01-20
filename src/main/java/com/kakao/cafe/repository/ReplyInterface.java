package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;

import java.util.List;

public interface ReplyInterface extends RepositoryInterface<Reply> {
    List<Reply> findByArticleId(Long articleId);
}
