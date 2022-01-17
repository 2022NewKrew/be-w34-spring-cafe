package com.kakao.cafe.post.application.port.out;

import com.kakao.cafe.post.domain.QuestionPost;

public interface UpdateQuestionPostPort {

    void update(QuestionPost questionPost);
}
