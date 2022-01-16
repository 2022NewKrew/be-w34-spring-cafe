package com.kakao.cafe.post.domain;

import com.kakao.cafe.post.domain.QuestionPost;

import java.util.List;
import java.util.Optional;

public interface QuestionPostRepository {

    QuestionPost save(QuestionPost questionPost);

    Optional<QuestionPost> findById(Long id);

    List<QuestionPost> findAll();

    void update(QuestionPost questionPost);

    void delete(Long id);

    void deleteAll();
}
