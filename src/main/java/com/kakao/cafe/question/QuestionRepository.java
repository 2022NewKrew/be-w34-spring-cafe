package com.kakao.cafe.question;

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
    Long save(Question question);

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
}
