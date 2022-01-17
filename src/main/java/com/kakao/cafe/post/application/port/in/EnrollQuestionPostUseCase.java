package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostSaveCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostSaveResult;

public interface EnrollQuestionPostUseCase {

    QuestionPostSaveResult enroll(QuestionPostSaveCommand command);
}
