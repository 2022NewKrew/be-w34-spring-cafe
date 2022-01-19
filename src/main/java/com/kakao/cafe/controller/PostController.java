package com.kakao.cafe.controller;

import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import com.kakao.cafe.model.user.UserDto;
import com.kakao.cafe.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getPost(@PathVariable long id, HttpSession session, Model model) {
        UserDto currentUser = getCurrentUserFromSession(session);
        PostDto post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("hasAuthority", post.getWriterId().equals(currentUser.getId()));
        return "post/show";
    }

    @GetMapping("/posts/{id}/update")
    public String getPostUpdateForm(@PathVariable long id, HttpSession session, Model model) {
        UserDto currentUser = getCurrentUserFromSession(session);
        model.addAttribute("post", postService.getPostById(id, currentUser.getId()));
        return "post/updateForm";
    }

    @PutMapping("/posts/{id}")
    public String updatePost(@PathVariable long id, @Valid PostWriteRequest request, HttpSession session, RedirectAttributes rttr) {
        UserDto currentUser = getCurrentUserFromSession(session);
        postService.updatePost(id, currentUser.getId(), request);
        rttr.addFlashAttribute("msg", "게시글을 수정하였습니다.");
        return "redirect:/posts/" + id;
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable long id, HttpSession session, RedirectAttributes rttr) {
        UserDto currentUser = getCurrentUserFromSession(session);
        postService.deletePost(id, currentUser.getId());
        rttr.addFlashAttribute("msg", "게시글을 삭제하였습니다.");
        return "redirect:/";
    }

    private UserDto getCurrentUserFromSession(HttpSession session) {
        return (UserDto) session.getAttribute("currentUser");
    }

}
