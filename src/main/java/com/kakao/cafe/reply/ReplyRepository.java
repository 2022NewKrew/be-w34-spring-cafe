package com.kakao.cafe.reply;

import java.sql.SQLException;
import java.util.List;

/**
 * 댓글 Repository interface
 *
 * @author jm.hong
 */
public interface ReplyRepository {

    Long save(Reply reply) throws SQLException;

    boolean deleteOne(Long id);

    List<Reply> findAllAsQuestionId(Long questionId);

    Reply findOne(Long id);

    boolean deleteAsQuestionId(Long id);
}
