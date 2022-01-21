package com.kakao.cafe.question;

import com.kakao.cafe.common.exception.BaseException;

import java.sql.SQLException;
import java.util.List;

/**
 * 질문글에 대한 Service Interface 입니다.
 *
 * @author jm.hong
 */
public interface QuestionService {
    /**
     * 데이터베이스에 저장합니다.
     * @param question 질문글에대한 엔티티
     * @return Database key 값을 반환
     */
    Long save(Question question) throws SQLException;

    /**
     * 데이터베이스의 key 값에 해당하는 엔티티를 반환합니다.
     * @param id 질문글 Table 에대한 Key 값
     * @return key 값에 해당하는 질문글을 반환
     */
    Question findOne(Long id);

    /**
     * 질문글에대한 모든 데이터를 반환합니다.
     * @return 질문글에대한 모든데이터를 반환
     */
    List<Question> findAll();

    /**
     * key값 하나로 테이블을 삭제한다.
     * @param id 삭제하고 싶은 table key
     * @return 삭제 성공 여부
     */
    boolean deleteOneWidthAdmin(Long id);

    /**
     * key값과 memberId값으로 삭제한다. (해당 테이블의 소유권자만 삭제 가능)
     * @param id 삭제하고 싶은 table의 키 값
     * @param memberId 삭제하고 싶은 테이블의 외래키
     * @return 삭제 성공 여부
     */
    boolean deleteOne(Long id, Long memberId) throws BaseException;

    /**
     * Question에 담긴 정보를 기준으로 수정합니다.
     * @param question 수정한 Question
     * @return 삭제 성공 여부findPage
     */
    boolean updateOne(Question question) throws BaseException;

    /**
     *
     * @param currentPage 조회할 페이지
     * @param pageSize 하나의 페이지에 보일 수
     * @return 하나의 페이지에 질문글
     */
    List<Question> findPage(int currentPage, int pageSize) throws BaseException;

    /**
     * pageSize의 크기로 endPage의 값을 계산합니다.
     * @return
     */
    int findEndPage(int pageSize);

}
