package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostClickCommand;

public interface UpdateQuestionPostUseCase {

    void clickPost(QuestionPostClickCommand command);
}
