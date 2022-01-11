package com.kakao.cafe.controller;

import com.kakao.cafe.model.Post;
import com.kakao.cafe.service.CafePostService;
import com.kakao.cafe.service.CafePostServiceImpl;
import com.kakao.cafe.url.PostViewURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class CafePostController {

    CafePostService cafePostService = new CafePostServiceImpl();

    @GetMapping("/write")
    String postViewWrite() {
        return "/post/form";
    }

    @PostMapping("/write")
    String writePost (Post newPost) {
        cafePostService.writePost(newPost);
        return "redirect:/posts/list";
    }

    @GetMapping("/list")
    String getPostList(Model model) {
        List<Post> postList = cafePostService.getPostList();
        model.addAttribute("postList", postList);
        return "/index";
    }

    @GetMapping("/content/{postId}")
    String getPostContent(Model model, @PathVariable("postId") String postId) {
        Post post = cafePostService.getPostContent(postId);
        model.addAttribute("post", post);
        return "/post/show";
    }


}
