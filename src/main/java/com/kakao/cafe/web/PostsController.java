package com.kakao.cafe.web;

import com.kakao.cafe.service.PostsService;
import com.kakao.cafe.web.dto.PostResponseDto;
import com.kakao.cafe.web.dto.PostsCreateRequestDto;
import com.kakao.cafe.web.dto.UserResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        if (httpSession.getAttribute("sessionedUser") != null) {
            PostResponseDto post = postsService.findById(index);
            model.addAttribute("post", post);
            return "post/show";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/post-article")
    public String post_form(HttpSession httpSession) {
        if (httpSession.getAttribute("sessionedUser") != null) {
            return "post/form";
        }
        return "redirect:/login";
    }

}
