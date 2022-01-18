package com.kakao.cafe.post.application.port.out;

import com.kakao.cafe.post.application.dto.command.QuestionPostDeleteCommand;

public interface DeleteQuestionPostPort {

    void delete(Long id);
}
