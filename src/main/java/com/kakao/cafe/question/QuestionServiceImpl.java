package com.kakao.cafe.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * QuestionService 의 구현체입니다.
 *
 * @author jm.hong
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 질문글을 저장합니다. 저장시에 현제 시스템 시간정보를 저장합니다.
     *
     * @param question 질문글에대한 엔티티
     * @return key 값에 해당하는 질문글을 반환
     */
    @Override
    public Long save(Question question) {

        Long id = null;

        try {
            id = questionRepository.save(question);
        } catch (SQLException e) {
            log.error("QUESTION TABLE SAVE 실패 SQLState : {}", e.getSQLState());
        }

        return id;
    }

    @Override
    public Question findOne(Long id) {
        return questionRepository.findOne(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
