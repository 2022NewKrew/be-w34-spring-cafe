package com.kakao.cafe.post.adapter.in.web;

import com.kakao.cafe.post.application.dto.result.QuestionPostDetailResult;
import com.kakao.cafe.post.application.QuestionPostService;
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

    private final QuestionPostService questionPostService;

    @GetMapping("/{id}/detail")
    public String postDetail(@PathVariable(name = "id") Long id, Model model, HttpServletRequest request) {
        QuestionPostDetailResult postDetail = questionPostService.getPostDetail(id);
        questionPostService.clickPost(id);
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
