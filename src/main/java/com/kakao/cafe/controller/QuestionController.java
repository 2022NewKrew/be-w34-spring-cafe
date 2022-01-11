package com.kakao.cafe.controller;

import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import com.kakao.cafe.question.dto.QuestionCreateDto;
import com.kakao.cafe.question.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String insertQuestion(@ModelAttribute("question") QuestionCreateDto questionCreateDto) {

        Question question = modelMapper.map(questionCreateDto, Question.class);
        Long id = questionService.save(question);

        return "redirect:/";
    }

    @GetMapping
    public String viewQuestionList(Model model) {

        List<QuestionDto> questions = questionService.findAll()
                .stream()
                .map(q -> modelMapper.map(q, QuestionDto.class))
                .collect(Collectors.toList());

        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());

        return "qna/show";

    }

    @GetMapping("/create")
    public String viewQuestionForm() {
        return "qna/form";
    }
}
