package com.kakao.cafe.post.application.port.out;

import com.kakao.cafe.post.domain.QuestionPost;

import java.util.List;
import java.util.Optional;

public interface LoadQuestionPostPort {

    List<QuestionPost> findAll();

    Optional<QuestionPost> findById(Long id);
}
