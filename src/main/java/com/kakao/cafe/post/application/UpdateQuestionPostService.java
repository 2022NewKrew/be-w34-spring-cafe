package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostClickCommand;
import com.kakao.cafe.post.application.dto.command.QuestionPostUpdateCommand;
import com.kakao.cafe.post.application.port.in.UpdateQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.LoadQuestionPostPort;
import com.kakao.cafe.post.application.port.out.UpdateQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.exception.QuestionPostErrorCode;
import com.kakao.cafe.post.exception.QuestionPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateQuestionPostService implements UpdateQuestionPostUseCase {

    private final LoadQuestionPostPort loadQuestionPostPort;
    private final UpdateQuestionPostPort updateQuestionPostPort;

    @Override
    public void clickPost(QuestionPostClickCommand command) {
        QuestionPost questionPost = loadQuestionPostPort.findById(command.getQuestionPostId())
                .orElseThrow(() -> new QuestionPostException(QuestionPostErrorCode.ID_NOT_FOUND));
        questionPost.viewCountIncrease();
        updateQuestionPostPort.update(questionPost);
    }

    @Override
    public void updatePost(QuestionPostUpdateCommand command) {
        QuestionPost questionPost = loadQuestionPostPort.findById(command.getPostId())
                .orElseThrow(() -> new QuestionPostException(QuestionPostErrorCode.ID_NOT_FOUND));
        questionPost.updateTitle(command.getTitle());
        questionPost.updateContent(command.getContent());
        updateQuestionPostPort.update(questionPost);
    }
}
