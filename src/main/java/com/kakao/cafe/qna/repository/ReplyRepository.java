package com.kakao.cafe.qna.repository;

import com.kakao.cafe.qna.domain.Reply;
import java.util.List;

public interface ReplyRepository {

    List<Reply> findByQnaId(long qnaId);

    void create(Reply reply);

    void delete(long id, String userId);
}
