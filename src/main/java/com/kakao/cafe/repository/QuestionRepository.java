package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    Question save(Question question);
    Optional<Question> findById(Long id);
    List<Question> findAll();
}
