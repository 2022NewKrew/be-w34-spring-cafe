package com.kakao.cafe.repository;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.entity.Reply;

import java.util.List;

public interface ReplyRepository {
    Reply save(Reply reply);
    List<Reply> findByArticleId(String articleId);
    Reply findById(String replyId);
    void deleteById(String index);
}
