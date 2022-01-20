package com.kakao.cafe.controller;

import com.kakao.cafe.dto.question.QuestionSaveDto;
import com.kakao.cafe.dto.question.QuestionUpdateDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.exception.NotAuthorizedException;
import com.kakao.cafe.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        questionService.save(QuestionSaveDto.builder()
                .userId(sessionUser.getId())
                .title(title)
                .contents(contents)
                .build());
        return "redirect:/";
    }

    @GetMapping("/questions/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "qna/show";
    }

    @GetMapping("/questions/{id}/form")
    public String serveUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("question", questionService.findById(id));
        return "qna/updateForm";
    }

    @PutMapping("/questions/{id}")
    public String update(@PathVariable int id, @ModelAttribute() QuestionUpdateDto questionUpdateDto, HttpSession session) {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionUser");
        if (sessionUser.getId() != id){
            throw new NotAuthorizedException("로그인된 사용자 정보와 수정하려는 질문글의 작성자 정보가 다릅니다.");
        }
        questionService.update(id, questionUpdateDto);
        return "redirect:/questions/"+id;
    }

    @DeleteMapping("/questions/{id}")
    public String deleteById(@PathVariable int id, HttpSession session) {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionUser");
        int userId =  questionService.findById(id).getUserId();
        if (sessionUser.getId() != userId){
            throw new NotAuthorizedException("로그인된 사용자 정보와 삭제하려는 질문글의 작성자 정보가 다릅니다.");
        }
        questionService.deleteById(id, userId);
        return "redirect:/";
    }
}
