package com.kakao.cafe.controller;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.service.CafePostService;
import org.springframework.lang.NonNull;
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
    final CafePostService cafePostService;
    public CafePostController(CafePostService cafePostService) {
        this.cafePostService = cafePostService;
    }

    private static final String POST_DIRECTORY = "post";
    public static final String POST_VIEW_LIST = POST_DIRECTORY+"/list";
    public static final String POST_VIEW_WRITE = POST_DIRECTORY+"/form";
    public static final String POST_VIEW_CONTENT = POST_DIRECTORY+"/show";

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String POST_REDIRECT_LIST = REDIRECT_PREFIX+"/posts/list";
    private static final String POST_REDIRECT_WRITE_FAIL = REDIRECT_PREFIX+"/posts/write/fail";

    @GetMapping("/write")
    String postViewWrite() {
        return POST_VIEW_WRITE;
    }

    @PostMapping("/write")
    String writePost (@NonNull Post newPost) {
        if(cafePostService.writePost(newPost)) {
            return POST_REDIRECT_LIST;
        }
        return POST_REDIRECT_WRITE_FAIL;
    }

    @GetMapping("/list")
    String getPostList(Model model) {
        List<Post> postList = cafePostService.getPostList();
        model.addAttribute("postList", postList);
        model.addAttribute("postCnt", CollectionHelper.getItemNumberOfList(postList));
        return POST_VIEW_LIST;
    }

    @GetMapping("/content/{postId}")
    String getPostContent(Model model, @NonNull @PathVariable("postId") int postId) {
        Post post = cafePostService.getPostContent(postId);
        model.addAttribute("post", post);
        return POST_VIEW_CONTENT;
    }
}
