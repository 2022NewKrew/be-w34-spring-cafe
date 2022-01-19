package com.kakao.cafe.controller;

import com.kakao.cafe.dto.post.PostCreateDto;
import com.kakao.cafe.dto.post.PostDetailDto;
import com.kakao.cafe.dto.post.PostUpdateDto;
import com.kakao.cafe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/questions")
    public String createQuestion(HttpSession session, PostCreateDto postCreateDto) {
        postService.create(postCreateDto, session);
        return "redirect:/";
    }

    @GetMapping("/questions/{questionId}")
    public String getQuestion(Model model, @PathVariable int questionId) {
        PostDetailDto post = postService.get(questionId);
        model.addAttribute("post", post);
        return "qna/show";
    }

    @GetMapping("/questions/{questionId}/update")
    public String qnaUpdateForm(Model model, @PathVariable int questionId, HttpSession session) {
        PostDetailDto post = postService.getUpdate(questionId, session);
        model.addAttribute("post", post);
        return "qna/update";
    }

    @PutMapping("/questions/{questionId}")
    public String updateQuestion(@PathVariable int questionId,
                                 PostUpdateDto postUpdateDto,
                                 HttpSession session) {
        postService.update(questionId, postUpdateDto, session);
        return "redirect:/questions/" + questionId;
    }

    @DeleteMapping("/questions/{questionId}")
    public String deleteQuestion(@PathVariable int questionId, HttpSession session) {
        postService.delete(questionId, session);
        return "redirect:/";
    }
}
