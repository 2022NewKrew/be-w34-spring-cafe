package com.kakao.cafe.reply;

import com.kakao.cafe.common.exception.BaseException;

import java.sql.SQLException;
import java.util.List;

/**
 * 댓글에대한 ReplyService 인터페이스 입니다.
 *
 * @author jm.hong
 */
public interface ReplyService {
    /**
     * 댓글을 저장합니다.
     *
     * @param reply
     * @return
     * @throws SQLException
     */
    Long save(Reply reply) throws SQLException;

    /**
     * 댓글을 삭제합니다.
     * @param id 삭제할 댓글의 key 값
     * @param memberId 현재 로그인한 회원 key
     * @param questionId 댓글이 참조하고 있는 질문글 key
     * @return 댓글 삭제에 대한 성공 여부를 반환
     * @throws BaseException 해당 댓글에대한 권한이 없을 경우 예외를 발생합니다.
     */
    boolean deleteOne(Long id, Long memberId, Long questionId) throws BaseException;

    /**
     * 게시글에 해당하는 상태가 DELETE가 아닌 모든 댓글들을 가져옵니다.
     * @param questionId 게시글 key 값
     * @return 게시글에 해당하는 댓글
     */
    List<Reply> findAllAsQuestionId(Long questionId);
}
