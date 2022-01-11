package com.kakao.cafe.domain.post;

import java.util.List;
import java.util.Optional;

public interface QuestionPostRepository {

    QuestionPost save(QuestionPost questionPost);

    Optional<QuestionPost> findById(Long id);

    List<QuestionPost> findAll();

    void delete(Long id);

    void deleteAll();
}
