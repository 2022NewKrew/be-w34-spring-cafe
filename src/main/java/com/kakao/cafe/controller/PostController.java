package com.kakao.cafe.controller;

import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public String uploadPost(@Valid PostWriteRequest post, HttpSession session, RedirectAttributes rttr) {
        UserDto user = (UserDto) session.getAttribute("currentUser");
        postService.writePost(post, user.getId());
        rttr.addFlashAttribute("msg", "게시글을 등록하였습니다.");
        return "redirect:/";
    }

    @GetMapping("/")
    public String getAllPosts(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("sizeOfPosts", posts.size());
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "post/show";
    }

}
