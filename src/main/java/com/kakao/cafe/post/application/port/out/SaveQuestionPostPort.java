package com.kakao.cafe.post.application.port.out;

import com.kakao.cafe.post.domain.QuestionPost;

public interface SaveQuestionPostPort {

    QuestionPost save(QuestionPost questionPost);
}
