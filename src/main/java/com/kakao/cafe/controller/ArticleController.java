package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QuestionCreateRequest;
import com.kakao.cafe.dto.QuestionDetailResponse;
import com.kakao.cafe.dto.QuestionUpdateRequest;
import com.kakao.cafe.service.ArticleService;
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
public class ArticleController {

    private final ArticleService articleService ;

    @PostMapping("")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question, HttpSession session){
        Long userId = (Long)session.getAttribute(SessionConst.LOGIN_USER);
        articleService.saveQuestion(userId, question, LocalDateTime.now());
        return "redirect:/";
    }
    @GetMapping("{id}")
    public String viewArticle(@PathVariable Long id, Model model){
        model.addAttribute("article", articleService.findOneQuestion(id));
        return "qna/show";
    }
    @GetMapping("")
    public String viewArticleList(Model model){
        model.addAttribute("questions", articleService.findAllQuestions());
        return "index";
    }
    @GetMapping("/{id}/form")
    public String viewUpdateForm(@PathVariable Long id, Model model, HttpSession session){
        QuestionDetailResponse article = articleService.findOneQuestion(id);
        if(article.getUserId() != (Long)session.getAttribute(SessionConst.LOGIN_USER)){
            throw new IllegalStateException("글 작성자만 수정할 수 있습니다.");
        }
        model.addAttribute("article", article);
        return "qna/update-form";
    }
    @PutMapping("")
    public String updateQuestion(@ModelAttribute QuestionUpdateRequest question, HttpSession session){
        Long userId = (Long) session.getAttribute(SessionConst.LOGIN_USER);
        return "redirect:/questions/"+articleService.updateQuestion(userId, question);
    }

}
