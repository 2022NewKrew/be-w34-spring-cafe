package com.kakao.cafe.question;

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
    Long save(Question question);

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
}
