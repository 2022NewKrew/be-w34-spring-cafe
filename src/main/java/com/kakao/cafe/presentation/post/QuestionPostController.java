package com.kakao.cafe.presentation.post;

import com.kakao.cafe.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.application.post.QuestionPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class QuestionPostController {

    private final QuestionPostService questionPostService;

    @GetMapping("/{id}/detail")
    public String postDetail(@PathVariable(name = "id") Long id, Model model) {
        QuestionPostDetailResult postDetail = questionPostService.getPostDetail(id);
        questionPostService.clickPost(id);
        model.addAttribute("post", postDetail);
        return "qnadetail";
    }
}
