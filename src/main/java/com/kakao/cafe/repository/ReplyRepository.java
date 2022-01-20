package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyListResponse;

import java.util.List;

public interface ReplyRepository {
    void save(Reply reply);
    List<ReplyListResponse> findAllByQuestionId(Long questionId);
    void updateIsDeleted(Reply reply);
}
