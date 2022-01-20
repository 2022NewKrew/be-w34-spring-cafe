package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.comment.application.dto.command.GetRelatedPostCommentCommand;
import com.kakao.cafe.comment.application.dto.result.GetRelatedPostCommentResult;
import com.kakao.cafe.comment.application.port.in.GetCommentUseCase;
import com.kakao.cafe.post.application.dto.command.QuestionPostDetailCommand;
import com.kakao.cafe.post.application.dto.command.QuestionPostSameAuthorCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.post.application.port.in.GetQuestionPostUseCase;
import com.kakao.cafe.post.application.port.in.SameAuthorQuestionPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class QuestionPostController {

    private final GetQuestionPostUseCase getQuestionPostUseCase;
    private final GetCommentUseCase getCommentUseCase;
    private final SameAuthorQuestionPostUseCase sameAuthorQuestionPostUseCase;

    @GetMapping("/{post-id}/detail")
    public String postDetail(
            @PathVariable(name = "post-id") Long postId,
            Model model,
            @SessionAttribute(name = "user-id") Long userAccountId) {

        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(
                new QuestionPostDetailCommand(postId));

        GetRelatedPostCommentResult relatedComment = getCommentUseCase.getRelatedPost(
                new GetRelatedPostCommentCommand(postId));

        model.addAttribute("post", postDetail);
        model.addAttribute("comment", relatedComment.getCommentResults());

        if(sameAuthorQuestionPostUseCase.isSameAuthor(new QuestionPostSameAuthorCommand(postId, userAccountId)).isSame()) {
            return "updateqnadetail";
        }
        return "qnadetail";
    }

    @GetMapping("/{post-id}/updateform")
    public String updatePost(@PathVariable(name = "post-id") Long postId, Model model) {
        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(new QuestionPostDetailCommand(postId));

        model.addAttribute("post", postDetail);

        return "qnaupdateform";
    }
}
