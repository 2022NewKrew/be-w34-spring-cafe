package com.kakao.cafe.adapter.out.infra.persistence.reply;

import com.kakao.cafe.domain.article.Reply;
import java.util.List;

public interface ReplyRepository {

    void save(Reply reply);

    List<Reply> getAllReplyList();
}
