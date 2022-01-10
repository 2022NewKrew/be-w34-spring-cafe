package com.kakao.cafe.question;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionRepositoryHash implements QuestionRepository {

    private static Map<Long, Question> inMemoryDatabase = new HashMap<>();
    private static long autoIncrementNumber = 0L;

    @Override
    public Long save(Question question) {
        question.setId(autoIncrementNumber);
        inMemoryDatabase.put(autoIncrementNumber, question);
        return autoIncrementNumber++;
    }

    @Override
    public Question findOne(Long id) {
        return inMemoryDatabase.get(id);
    }

    @Override
    public List<Question> findAll() {
        // TODO stream 으로 변경
        List<Question> questions = new ArrayList<>();
        List<Long> keys = new ArrayList<>(inMemoryDatabase.keySet());

        for (Long key : keys) {
            questions.add(inMemoryDatabase.get(key));
        }

        return questions;
    }
}
