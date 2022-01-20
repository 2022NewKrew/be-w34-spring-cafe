package com.kakao.cafe.reply;

import java.sql.SQLException;
import java.util.List;

/**
 * 댓글 Repository interface
 *
 * @author jm.hong
 */
public interface ReplyRepository {
    /**
     * 질문글에 대한 댓글을 저장합니다.
     * @param reply 저장할 객체 엔티티
     * @return 저장한 객체의 key값 반환
     * @throws SQLException
     */
    Long save(Reply reply) throws SQLException;

    /**
     * 댓글에대한 상태를 DELETE로 변경합니다.
     * @param id 삭제할 댓글의 key 값
     * @return 삭제 성공 여부
     */
    boolean deleteOne(Long id);

    /**
     * 질문글 엔티티에 해당하는 댓글을 가져옵니다. 상태가 DELETE 가 아닌 모든 데이터를 조회합니다.
     * @param questionId
     * @return
     */
    List<Reply> findAllAsQuestionId(Long questionId);

    /**
     * 상태가 DELETE가 아닌 댓글을 가져옵니다.
     * @param id 찾고싶은 댓글 key 값
     * @return Key값에 해당하는 댓글 엔티티
     */
    Reply findOne(Long id);

    /**
     * 게시글에 등록된 모든 댓글들의 상태를 DELETE 로 변경합니다.
     * @param questionId 게시글 엔티티 key 값
     * @return 성공여부를 반환합니다.
     */
    boolean deleteAsQuestionId(Long questionId);
}
