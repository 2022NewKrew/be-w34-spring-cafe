package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostDeleteCommand;

public interface DeleteQuestionPostUseCase {

    void deletePost(QuestionPostDeleteCommand command);
}
