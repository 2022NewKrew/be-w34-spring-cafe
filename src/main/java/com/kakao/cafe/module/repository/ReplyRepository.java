package com.kakao.cafe.module.repository;

import com.kakao.cafe.module.model.domain.Reply;

import java.util.List;

import static com.kakao.cafe.module.model.dto.ReplyDtos.*;

public interface ReplyRepository {

    void addReply(Reply reply);

    List<ReplyReadDto> findRepliesByArticleId(Long id);

    Reply findReplyById(Long id);

    void deleteReply(Long id);
}
