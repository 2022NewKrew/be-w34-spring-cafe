package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.entity.ReplyDraft;

import java.util.List;

public interface ReplyRepository {

    Reply create(ReplyDraft draft);
    List<Reply> list(long targetId);
}
