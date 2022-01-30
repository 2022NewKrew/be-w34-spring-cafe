package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;

import java.util.List;

public interface ReplyRepository {

    Reply save(Reply form);
    List<Reply> findByArticleID(Long articleID);

}
