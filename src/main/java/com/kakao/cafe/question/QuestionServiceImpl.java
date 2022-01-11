package com.kakao.cafe.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * QuestionService 의 구현체입니다.
 *
 * @author jm.hong
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 질문글을 저장합니다. 저장시에 현제 시스템 시간정보를 저장합니다.
     * @param question 질문글에대한 엔티티
     * @return key 값에 해당하는 질문글을 반환
     */
    @Override
    public Long save(Question question) {

        question.updateTime();

        return questionRepository.save(question);

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
