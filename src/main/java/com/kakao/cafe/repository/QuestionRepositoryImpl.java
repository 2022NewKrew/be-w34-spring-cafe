package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class QuestionRepositoryImpl implements QuestionRepository{

    private static Map<Long, Question> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Question save(Question question) {
        question.setId(++sequence);
        store.put(question.getId(), question);
        return question;
    }

    @Override
    public Optional<Question> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Question> findAll() {
        return null;
    }
}
