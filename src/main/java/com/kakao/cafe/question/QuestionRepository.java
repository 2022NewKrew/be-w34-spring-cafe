package com.kakao.cafe.question;

import java.sql.SQLException;
import java.util.List;

/**
 * 질문글에 대한 Repository Interface 입니다.
 *
 * @author jm.hong
 */
public interface QuestionRepository {
    /**
     * 질문글을 데이터베이스에 저장합니다.
     * @param question 질문글에 대한 정보를 담은 엔티티 입니다.
     * @return DataBased에 저장된 Key값을 반환합니다.
     */
    Long save(Question question) throws SQLException;

    /**
     * key 값에 해당하는 질문글 엔티티를 불러옵니다.
     * @param id 질문글 Table 의 Key 값
     * @return 질문글 엔티티
     */
    Question findOne(Long id);

    /**
     * 등록된 모든 질문글을 불러옵니다.
     * @return 데이터베이스에 저장된 모든 질문글.
     */
    List<Question> findAll();

    /**
     * key값 하나로 테이블을 삭제한다.
     * @param id 삭제하고 싶은 table key
     * @return 삭제 성공 여부
     */
    boolean deleteOne(Long id);

    /**
     * Question에 담긴 정보를 기준으로 수정합니다.
     * @param question 수정한 Question
     * @return 삭제 성공 여부
     */
    boolean updateOne(Question question);
}
