package com.kakao.cafe.repository.reply;

import com.kakao.cafe.domain.Reply;

import java.util.List;

public interface ReplyRepository {

    void save(Reply reply);

    List<Reply> findByArticleId(Long articleId);

    void deleteById(Long id);
}
