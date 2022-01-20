package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Question;
import com.kakao.cafe.controller.dto.QuestionDetailResponse;
import com.kakao.cafe.controller.dto.QuestionListResponse;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {
    void save(Question question);
    Long update(Question question);
    Optional<Question> findById(Long id);
    Optional<QuestionDetailResponse> findDetailById(Long id);
    List<Question> findAll();
    List<QuestionListResponse> findAllAndWriterNickname();
    void updateIsDeleted(Question question);
}
