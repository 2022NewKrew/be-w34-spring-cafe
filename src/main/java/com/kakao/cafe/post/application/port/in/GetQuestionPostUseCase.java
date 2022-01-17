package com.kakao.cafe.post.application.port.in;

import com.kakao.cafe.post.application.dto.command.QuestionPostDetailCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;

public interface GetQuestionPostUseCase {

    QuestionPostDetailResult getPostDetail(QuestionPostDetailCommand command);

    QuestionPostDetailListResult getAllPost();
}
