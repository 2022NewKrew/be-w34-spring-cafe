package com.kakao.cafe.controller;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.question.QuestionSaveDto;
import com.kakao.cafe.dto.question.QuestionUpdateDto;
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
        User user = (User) session.getAttribute("sessionedUser");
        System.out.println(user.getId());
        questionService.save(QuestionSaveDto.builder()
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

    @GetMapping("/questions/{id}/form")
    public String serveUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("question", questionService.findbyId(id));
        return "qna/updateForm";
    }

    @PutMapping("/questions/{id}")
    public String update(@PathVariable int id, @ModelAttribute() QuestionUpdateDto questionUpdateDto, HttpSession session) {
        User user = (User)session.getAttribute("sessionedUser");
        if (user.getId() != id){
            throw new IllegalArgumentException("로그인된 사용자 정보와 수정하려는 게시글의 사용자 정보가 다릅니다.");
        }
        questionService.update(id, questionUpdateDto);
        return "redirect:/questions/"+id;
    }
}
