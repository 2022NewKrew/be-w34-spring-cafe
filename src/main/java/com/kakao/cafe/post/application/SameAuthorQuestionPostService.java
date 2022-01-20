package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostSameAuthorCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostSameAuthorResult;
import com.kakao.cafe.post.application.port.in.SameAuthorQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.LoadQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.exception.QuestionPostErrorCode;
import com.kakao.cafe.post.exception.QuestionPostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SameAuthorQuestionPostService implements SameAuthorQuestionPostUseCase {

    private final LoadQuestionPostPort loadQuestionPostPort;

    @Override
    public QuestionPostSameAuthorResult isSameAuthor(QuestionPostSameAuthorCommand command) {
        QuestionPost questionPost = loadQuestionPostPort.findById(command.getQuestionPostId())
                .orElseThrow(() -> new QuestionPostException(QuestionPostErrorCode.ID_NOT_FOUND));
        return new QuestionPostSameAuthorResult(questionPost.isSameAuthor(command.getUserAccountId()));
    }

}
