package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Question;

import java.util.*;

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
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(store.values());
    }
}
