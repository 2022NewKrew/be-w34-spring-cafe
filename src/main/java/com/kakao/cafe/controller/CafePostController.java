package com.kakao.cafe.controller;

import com.kakao.cafe.model.Post;
import com.kakao.cafe.service.CafePostService;
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
@RequestMapping("/post")
public class CafePostController {

    @Autowired
    CafePostService cafePostService;

    @PostMapping()
    String writePost (Post newPost) {
        cafePostService.writePost(newPost);
        return PostViewURL.POST_WRITE.getMappingUrl();
    }

    @GetMapping("/list")
    String getPostList(Model model) {
        List<Post> postList = cafePostService.getPostList();
        model.addAttribute(postList);
        return PostViewURL.POST_GET_LIST_VIEW.getMappingUrl();
    }

    @GetMapping("/content/{postId}")
    String getPostContent(Model model, @PathVariable("postId") String postId) {
        Post post = cafePostService.getPostContent(postId);
        model.addAttribute(post);
        return PostViewURL.POST_GET_CONTENT_VIEW.getMappingUrl();
    }


}
