package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostDetailCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.post.application.port.in.GetQuestionPostUseCase;
import com.kakao.cafe.post.application.port.out.LoadQuestionPostPort;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.exception.QuestionPostErrorCode;
import com.kakao.cafe.post.exception.QuestionPostException;
import com.kakao.cafe.util.DateTimeFormatUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetQuestionPostService implements GetQuestionPostUseCase {

    private final LoadQuestionPostPort loadQuestionPostPort;

    @Override
    public QuestionPostDetailResult getPostDetail(QuestionPostDetailCommand command) {
        QuestionPost questionPost = loadQuestionPostPort.findById(command.getQuestionPostId())
                .orElseThrow(() -> new QuestionPostException(QuestionPostErrorCode.ID_NOT_FOUND));

        return new QuestionPostDetailResult(
                questionPost.getQuestionPostId(),
                questionPost.getTitle(),
                questionPost.getContent(),
                DateTimeFormatUtil.format(questionPost.getCreatedAt()),
                questionPost.getViewCount(),
                questionPost.getUserAccount().getUsername());
    }

    @Override
    public QuestionPostDetailListResult getAllPost() {
        List<QuestionPost> questionPosts = loadQuestionPostPort.findAll();

        List<QuestionPostDetailResult> result = questionPosts.stream()
                .map(post -> new QuestionPostDetailResult(
                        post.getQuestionPostId(),
                        post.getTitle(),
                        post.getContent(),
                        DateTimeFormatUtil.format(post.getCreatedAt()),
                        post.getViewCount(),
                        post.getUserAccount().getUsername())
                )
                .collect(Collectors.toList());

        return new QuestionPostDetailListResult(result);
    }
}
