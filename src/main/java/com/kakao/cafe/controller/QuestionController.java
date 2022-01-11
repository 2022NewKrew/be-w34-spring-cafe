package com.kakao.cafe.controller;

import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/create")
    public String insertQuestion(Question question) {

        Long id = questionService.save(question);

        return "redirect:/";
    }

    @GetMapping
    public String viewQuestionList(Model model) {

        List<Question> questions = questionService.findAll();

        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());

        return "qna/show";

    }

    @GetMapping("/create")
    public String viewQuestionForm() {
        return "qna/form";
    }
}
