package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostClickCommand;
import com.kakao.cafe.post.application.dto.command.QuestionPostUpdateCommand;

public interface UpdateQuestionPostUseCase {

    void clickPost(QuestionPostClickCommand command);

    void updatePost(QuestionPostUpdateCommand command);
}
