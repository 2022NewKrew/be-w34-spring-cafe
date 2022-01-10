package com.kakao.cafe.question;

import java.util.List;

public interface QuestionService {
    Long save(Question question);
    Question findOne(Long id);
    List<Question> findAll();
}
