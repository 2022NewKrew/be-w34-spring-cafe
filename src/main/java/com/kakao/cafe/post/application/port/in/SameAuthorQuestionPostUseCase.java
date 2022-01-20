package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostSameAuthorCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostSameAuthorResult;

public interface SameAuthorQuestionPostUseCase {

    QuestionPostSameAuthorResult isSameAuthor(QuestionPostSameAuthorCommand command);
}
