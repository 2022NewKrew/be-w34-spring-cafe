package com.kakao.cafe.service;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.domain.QuestionCreateRequest;
import com.kakao.cafe.repository.QuestionRepository;
import com.kakao.cafe.repository.QuestionRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final QuestionRepository questionRepository = new QuestionRepositoryImpl();

    public Long saveQuestion(QuestionCreateRequest questionDTO) {
        Question question = new Question(questionDTO);
        return questionRepository.save(question).getId();
    }
}
