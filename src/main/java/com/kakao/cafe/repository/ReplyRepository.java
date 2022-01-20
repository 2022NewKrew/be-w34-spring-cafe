package com.kakao.cafe.repository;

import com.kakao.cafe.entity.Reply;

import java.util.List;

public interface ReplyRepository {
    void save(Reply entity);

    void update(Reply entity);

    public List<Reply> findAllByArticleId(Long articleId);

    void deleteById(Long replyId);
}
