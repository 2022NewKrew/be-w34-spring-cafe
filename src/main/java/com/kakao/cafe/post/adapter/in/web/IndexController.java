package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.post.application.dto.result.QuestionPostDetailListResult;
import com.kakao.cafe.post.application.port.in.GetQuestionPostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final GetQuestionPostUseCase getQuestionPostUseCase;

    @GetMapping("")
    public String allPost(Model model, HttpSession httpSession) {
        QuestionPostDetailListResult allPost = getQuestionPostUseCase.getAllPost();
        model.addAttribute("count", allPost.getDetailResults().size());
        model.addAttribute("posts", allPost.getDetailResults());

        if(httpSession.getAttribute("user-id") != null) {
            return "after/afterindex";
        }
        return "index";
    }
}
