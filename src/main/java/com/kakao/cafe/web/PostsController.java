package com.kakao.cafe.web;

import com.kakao.cafe.web.dto.Post;
import com.kakao.cafe.web.dto.PostsCreateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class PostsController {

    private static final ArrayList<Post> posts = new ArrayList<>();

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("posts", posts);
        return "index";
    }

    @PostMapping("/posts")
    public String posts(PostsCreateRequestDto requestDto) {
        posts.add(new Post("tester", requestDto.getTitle(), requestDto.getContent(), 0, posts.size()));
        return "redirect:/";
    }

    @GetMapping("/posts/{index}")
    public String postsDetail(@PathVariable("index") int index, Model model) {
        System.out.println(posts.get(index));
        model.addAttribute("post", posts.get(index));
        return "post/show";
    }

}
