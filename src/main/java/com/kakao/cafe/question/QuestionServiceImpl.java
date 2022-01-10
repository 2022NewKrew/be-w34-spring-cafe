package com.kakao.cafe.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

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
