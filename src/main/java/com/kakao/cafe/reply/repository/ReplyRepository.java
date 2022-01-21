package com.kakao.cafe.reply.repository;

import com.kakao.cafe.reply.domain.Reply;
import java.util.List;

public interface ReplyRepository {

    void save(Reply reply);

    Reply findById(int id);

    List<Reply> findByArticle(int article);

    void delete(int id);
}
