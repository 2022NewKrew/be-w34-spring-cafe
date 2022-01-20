package com.kakao.cafe.controller;

import com.kakao.cafe.dto.*;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.service.QuestionService;
import com.kakao.cafe.web.meta.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final ReplyRepository replyRepository;

    @PostMapping("")
    public String createQuestion(@ModelAttribute QuestionCreateRequest question, HttpSession session){
        questionService.saveQuestion(getUserId(session), question, LocalDateTime.now());
        return "redirect:/";
    }
    @GetMapping("{id}")
    public String viewQuestionDetail(@PathVariable Long id, Model model){
        model.addAttribute("article", questionService.findOneQuestion(id));

        List<ReplyListResponse> replyList = replyRepository.findAllByQuestionId(id);
        model.addAttribute("numberOfReply", replyList.size());
        model.addAttribute("replyList", replyList);

        model.addAttribute("questionId", id);

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
    @DeleteMapping("/{questionId}/reply/{replyId}")
    public String deleteReply(@PathVariable Long questionId, @PathVariable Long replyId,
                              HttpSession session){
        questionService.deleteReply(questionId, replyId, getUserId(session));
        return "redirect:/questions/" + questionId;
    }
    @PostMapping("/reply")
    public String createReply(@ModelAttribute ReplyCreateRequest reply, HttpSession session){
        questionService.saveReply(getUserId(session), reply);
        return "redirect:/questions/" + reply.getQuestionId();
    }

    private Long getUserId(HttpSession session){
        return (Long) session.getAttribute(SessionConst.LOGIN_USER);
    }
}
