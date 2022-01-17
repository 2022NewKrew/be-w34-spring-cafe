package com.kakao.cafe.post.application;

import com.kakao.cafe.exception.IdNotFoundException;
import com.kakao.cafe.post.application.dto.command.QuestionPostClickCommand;
import com.kakao.cafe.post.application.port.in.UpdateQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.LoadQuestionPostPort;
import com.kakao.cafe.post.application.port.out.UpdateQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateQuestionPostService implements UpdateQuestionPostUseCase {

    private final LoadQuestionPostPort loadQuestionPostPort;
    private final UpdateQuestionPostPort updateQuestionPostPort;

    @Override
    public void clickPost(QuestionPostClickCommand command) {
        QuestionPost questionPost = loadQuestionPostPort.findById(command.getQuestionPostId())
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));
        questionPost.viewCountIncrease();
        updateQuestionPostPort.update(questionPost);
    }
}
