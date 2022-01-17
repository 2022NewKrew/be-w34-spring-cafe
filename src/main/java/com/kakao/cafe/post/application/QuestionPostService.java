package com.kakao.cafe.post.application;

import com.kakao.cafe.post.application.dto.command.QuestionPostSaveCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.exception.IdNotFoundException;
import com.kakao.cafe.user.application.UserAccountService;
import com.kakao.cafe.post.domain.QuestionPost;
import com.kakao.cafe.post.domain.QuestionPostRepository;
import com.kakao.cafe.user.domain.UserAccount;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@Transactional
public class QuestionPostService {

    private final QuestionPostRepository questionPostRepository;
    private final UserAccountService userAccountService;

    public QuestionPostService(@Qualifier("jdbc-question-db") QuestionPostRepository questionPostRepository, UserAccountService userAccountService) {
        this.questionPostRepository = questionPostRepository;
        this.userAccountService = userAccountService;
    }

    public QuestionPost save(QuestionPostSaveCommand command) {
        UserAccount userAccount = userAccountService.getUserInfo(command.getUserAccountId());
        return questionPostRepository.save(command.toEntity(userAccount));
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
                        post.getUserAccount().getUsername())
                )
                .collect(Collectors.toList());

        return new QuestionPostDetailListResult(result);
    }

    public QuestionPostDetailResult getPostDetail(Long id) {
        QuestionPost questionPost = questionPostRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));

        return new QuestionPostDetailResult(
                questionPost.getQuestionPostId(),
                questionPost.getTitle(),
                questionPost.getContent(),
                questionPost.getCreatedAt().format(ofPattern("yyyy-MM-dd HH:mm:ss")),
                questionPost.getViewCount(),
                questionPost.getUserAccount().getUsername()
        );
    }

    public void clickPost(Long id) {
        QuestionPost questionPost = questionPostRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("유효하지 않는 값입니다"));
        questionPost.viewCountIncrease();
        questionPostRepository.update(questionPost);
    }
}
