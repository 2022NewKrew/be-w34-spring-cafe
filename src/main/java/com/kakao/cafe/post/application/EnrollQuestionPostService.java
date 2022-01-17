package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostSaveCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostSaveResult;
import com.kakao.cafe.post.application.port.in.EnrollQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.SaveQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import com.kakao.cafe.user.exception.UserAccountErrorCode;
import com.kakao.cafe.user.exception.UserAccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollQuestionPostService implements EnrollQuestionPostUseCase {

    private final LoadUserAccountPort loadUserAccountPort;
    private final SaveQuestionPostPort saveQuestionPostPort;

    @Override
    public QuestionPostSaveResult enroll(QuestionPostSaveCommand command) {
        UserAccount user = loadUserAccountPort.findById(command.getUserAccountId())
                .orElseThrow(() -> new UserAccountException(UserAccountErrorCode.ID_NOT_FOUND));
        QuestionPost questionPost = saveQuestionPostPort.save(command.toEntity(user));
        return new QuestionPostSaveResult(questionPost.getQuestionPostId());
    }
}
