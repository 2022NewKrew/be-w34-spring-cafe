package com.kakao.cafe.web;

import com.kakao.cafe.service.PostsService;
import com.kakao.cafe.web.dto.PostResponseDto;
import com.kakao.cafe.web.dto.PostsCreateRequestDto;
import com.kakao.cafe.web.dto.UserResponseDto;
import org.h2.engine.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class PostsController {

    private final PostsService postsService = new PostsService();

    @GetMapping("/")
    public String mainPage(Model model) {
        ArrayList<PostResponseDto> posts = getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/api/posts")
    public ArrayList<PostResponseDto> getAllPosts() {
        return postsService.findAll();
    }

    @PostMapping("/api/posts")
    public String posts(PostsCreateRequestDto requestDto, HttpSession httpSession) {
        UserResponseDto userInfo = (UserResponseDto) httpSession.getAttribute("sessionedUser");
        String writer = userInfo.getName();
        requestDto.setWriter(writer);
        postsService.save(requestDto);

        return "redirect:/";
    }

    @GetMapping("/posts/{index}")
    public String postsDetail(@PathVariable("index") Long index, Model model, HttpSession httpSession) {
        UserResponseDto userInfo = (UserResponseDto) httpSession.getAttribute("sessionedUser");
        if (userInfo != null) {
            PostResponseDto post = postsService.findById(index);
            model.addAttribute("post", post);
            if (userInfo.getName() == post.getUser()) model.addAttribute("isWriter", true);
            return "post/show";
        }
        else {
            return "redirect:/login";
        }
    }

    @PutMapping("/api/posts/{index}")
    public String postsUpdate(@PathVariable("index") Long index, PostsCreateRequestDto requestDto) {
        postsService.update(index, requestDto);
        return "redirect:/";
    }

    @GetMapping("/post/form")
    public String post_form(HttpSession httpSession) {
        if (httpSession.getAttribute("sessionedUser") != null) {
            return "post/form";
        }
        return "redirect:/login";
    }

    @GetMapping("/posts/{index}/form")
    public String postUpdateForm(@PathVariable("index") Long index, Model model) {
        PostResponseDto postInfo = postsService.findById(index);
        model.addAttribute("post", postInfo);

        return "post/updateForm";
    }

    @DeleteMapping("/posts/{index}")
    public String deletePost(@PathVariable("index") Long id){
        postsService.deleteById(id);

        return "redirect:/";
    }

}
