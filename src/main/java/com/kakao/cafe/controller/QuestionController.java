package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.question.QuestionRequestDto;
import com.kakao.cafe.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String findAll(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "index";
    }

    @PostMapping("/questions")
    public String save(@RequestParam String title, @RequestParam String contents, HttpSession session) {
        User user = (User) session.getAttribute("sessionedUser");
        System.out.println(user.getId());
        questionService.save(QuestionRequestDto.builder()
                .userId(user.getId())
                .writer(user.getName())
                .title(title)
                .contents(contents)
                .build());
        return "redirect:/";
    }

    @GetMapping("/questions/{id}")
    public String findbyId(@PathVariable int id, Model model) {
        model.addAttribute("question", questionService.findbyId(id));
        return "qna/show";
    }
}
