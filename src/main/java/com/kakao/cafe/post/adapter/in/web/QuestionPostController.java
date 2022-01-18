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
    public String postDetail(@PathVariable(name = "post-id") Long id, Model model, HttpSession httpSession) {
        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(new QuestionPostDetailCommand(id));
        updateQuestionPostUseCase.clickPost(new QuestionPostClickCommand(id));
        model.addAttribute("post", postDetail);

        if(Objects.equals(httpSession.getAttribute("user-id"), postDetail.getUserAccountId())) {
            return "after/updateqnadetail";
        }
        if(httpSession.getAttribute("user-id") != null) {
            return "after/qnadetail";
        }
        return "before/qnadetail";
    }

    @GetMapping("/{post-id}/update")
    public String updatePost(@PathVariable(name = "post-id") Long id, Model model) {
        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(new QuestionPostDetailCommand(id));

        model.addAttribute("post", postDetail);

        return "after/qnaupdateform";
    }
}
