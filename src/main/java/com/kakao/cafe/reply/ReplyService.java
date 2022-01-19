package com.kakao.cafe.reply;

import com.kakao.cafe.common.exception.BaseException;

import java.sql.SQLException;
import java.util.List;

public interface ReplyService {
    Long save(Reply reply) throws SQLException;

    boolean deleteOne(Long id, Long memberId, Long questionId) throws BaseException;

    List<Reply> findAllAsQuestionId(Long questionId);
}
