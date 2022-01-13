package com.kakao.cafe.domain.question;

import java.util.List;

public interface QuestionRepository {
    void save(Question question);
    Question findById(int id);
    List<Question> findAll();
}
