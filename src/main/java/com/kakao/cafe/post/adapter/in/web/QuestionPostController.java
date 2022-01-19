package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.post.application.dto.command.QuestionPostClickCommand;
import com.kakao.cafe.post.application.dto.command.QuestionPostDetailCommand;
import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.post.application.port.in.GetQuestionPostUseCase;
import com.kakao.cafe.post.application.port.in.UpdateQuestionPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class QuestionPostController {

    private final GetQuestionPostUseCase getQuestionPostUseCase;
    private final UpdateQuestionPostUseCase updateQuestionPostUseCase;

    @GetMapping("/{post-id}/detail")
    public String postDetail(@PathVariable(name = "post-id") Long postId, Model model, HttpSession httpSession) {
        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(new QuestionPostDetailCommand(postId));
        updateQuestionPostUseCase.clickPost(new QuestionPostClickCommand(postId));
        model.addAttribute("post", postDetail);

        if(Objects.equals(httpSession.getAttribute("user-id"), postDetail.getUserAccountId())) {
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
