package com.kakao.cafe.application.post;

import com.kakao.cafe.application.dto.command.QuestionPostSaveCommand;
import com.kakao.cafe.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.application.exception.IdNotFoundException;
import com.kakao.cafe.application.user.UserAccountService;
import com.kakao.cafe.domain.post.QuestionPost;
import com.kakao.cafe.domain.post.QuestionPostRepository;
import com.kakao.cafe.domain.user.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@RequiredArgsConstructor
public class QuestionPostService {

    private final QuestionPostRepository questionPostRepository;
    private final UserAccountService userAccountService;

    public QuestionPost save(QuestionPostSaveCommand command) {
        return questionPostRepository.save(command.toEntity());
    }

    public QuestionPostDetailListResult getAllPost() {
        List<QuestionPost> questionPosts = questionPostRepository.findAll();

        List<QuestionPostDetailResult> result = questionPosts.stream()
                .map(post -> new QuestionPostDetailResult(
                        post.getQuestionPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCreatedAt().format(ofPattern("yyyy-MM-dd HH:mm:ss")),
                        post.getViewCount(),
                        userAccountService.getUserInfo(post.getUserAccountId()).getUsername())
                )
                .collect(Collectors.toList());

        return new QuestionPostDetailListResult(result);
    }

    public QuestionPostDetailResult getPostDetail(Long id) {
        QuestionPost questionPost = questionPostRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));

        UserAccount userAccount = userAccountService.getUserInfo(questionPost.getUserAccountId());

        return new QuestionPostDetailResult(
                questionPost.getQuestionPostId(),
                questionPost.getTitle(),
                questionPost.getContent(),
                questionPost.getCreatedAt().format(ofPattern("yyyy-MM-dd HH:mm:ss")),
                questionPost.getViewCount(),
                userAccount.getUsername()
        );
    }

    public void clickPost(Long id) {
        QuestionPost questionPost = questionPostRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));
        questionPost.viewCountIncrease();
    }
}
