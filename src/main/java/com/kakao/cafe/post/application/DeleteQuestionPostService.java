package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostDeleteCommand;
import com.kakao.cafe.post.application.port.in.DeleteQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.DeleteQuestionPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteQuestionPostService implements DeleteQuestionPostUseCase {

    private final DeleteQuestionPostPort deleteQuestionPostPort;

    @Override
    public void deletePost(QuestionPostDeleteCommand command) {
        deleteQuestionPostPort.delete(command.getPostId());
    }
}
