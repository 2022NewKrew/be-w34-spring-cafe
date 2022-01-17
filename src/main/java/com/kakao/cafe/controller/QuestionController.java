package com.kakao.cafe.controller;

import com.kakao.cafe.question.Question;
import com.kakao.cafe.question.QuestionService;
import com.kakao.cafe.question.dto.QuestionCreateDto;
import com.kakao.cafe.question.dto.QuestionDto;
import com.kakao.cafe.user.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    @PostMapping("/create")
    public String insertQuestion(HttpServletRequest request, @ModelAttribute("question") @Valid QuestionCreateDto questionCreateDto) {

        HttpSession httpSession = request.getSession();
        User user = modelMapper.map(httpSession.getAttribute("loginUser"), User.class);
        Question question = modelMapper.map(questionCreateDto, Question.class);

        question.setMemberId(user.getId());
        question.setWriter(user.getUserId());

        questionService.save(question);

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

        return "qna/list";

    }

    @GetMapping("/{id}")
    public String viewQuestionDetail(@PathVariable("id") Long id, Model model) {

        QuestionDto question = modelMapper.map(questionService.findOne(id), QuestionDto.class);

        model.addAttribute("question", question);

        return "qna/detail";

    }

    @GetMapping("/create")
    public String viewQuestionForm(Model model) {
        return "qna/form";
    }
}
