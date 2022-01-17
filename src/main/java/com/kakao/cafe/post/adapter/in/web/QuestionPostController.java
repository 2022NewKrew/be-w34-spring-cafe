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

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class QuestionPostController {

    private final GetQuestionPostUseCase getQuestionPostUseCase;
    private final UpdateQuestionPostUseCase updateQuestionPostUseCase;

    @GetMapping("/{id}/detail")
    public String postDetail(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        QuestionPostDetailResult postDetail = getQuestionPostUseCase.getPostDetail(new QuestionPostDetailCommand(id));
        updateQuestionPostUseCase.clickPost(new QuestionPostClickCommand(id));
        model.addAttribute("post", postDetail);
        if(request.getSession().getAttribute("user-id") != null) {
            return "after/qnadetail";
        }
        return "before/qnadetail";
    }

    @GetMapping("/form")
    public String writePost(Model model, HttpServletRequest request) {

        Long id = (Long) request.getSession().getAttribute("user-id");
        model.addAttribute("user-id", id);
        return "after/qnaform";
    }
}
