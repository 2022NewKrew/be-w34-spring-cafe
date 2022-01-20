package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.QuestionUpdateRequest;
import com.kakao.cafe.service.QuestionService;
import com.kakao.cafe.web.meta.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question, HttpSession session){
        questionService.saveQuestion(getUserId(session), question, LocalDateTime.now());
        return "redirect:/";
    }
    @GetMapping("{id}")
    public String viewQuestionDetail(@PathVariable Long id, Model model){
        model.addAttribute("article", questionService.findOneQuestion(id));
        return "qna/show";
    }
    @GetMapping("")
    public String viewQuestionList(Model model){
        model.addAttribute("questions", questionService.findAllQuestions());
        return "index";
    }
    @GetMapping("/{id}/form")
    public String viewUpdateForm(@PathVariable Long id, Model model, HttpSession session){
        QuestionDetailResponse article = questionService.findOneQuestion(id);
        if(article.getUserId() != getUserId(session)){
            throw new IllegalStateException("글 작성자만 수정할 수 있습니다.");
        }
        model.addAttribute("article", article);
        return "qna/update-form";
    }
    @PutMapping("")
    public String updateQuestion(@ModelAttribute QuestionUpdateRequest question, HttpSession session){
        return "redirect:/questions/" + questionService.updateQuestion(getUserId(session), question);
    }
    @DeleteMapping("{id}")
    public String deleteQuestion(@PathVariable Long id, HttpSession session){
        Long userId = getUserId(session);
        questionService.deleteQuestion(id, userId);
        return "redirect:/questions";
    }

    private Long getUserId(HttpSession session){
        return (Long) session.getAttribute(SessionConst.LOGIN_USER);
    }
}
