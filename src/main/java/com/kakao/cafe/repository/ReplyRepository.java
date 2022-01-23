package com.kakao.cafe.repository;

import com.kakao.cafe.domain.reply.Reply;

import java.util.List;

public interface ReplyRepository extends BaseRepository<Reply> {

    List<Reply> findAllByArticle(Long articleId);
}
