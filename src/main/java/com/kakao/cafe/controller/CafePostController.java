package com.kakao.cafe.controller;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.service.CafePostService;
import com.kakao.cafe.url.PostRedirect;
import com.kakao.cafe.url.PostView;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(PostRedirect.POST_BASE_URL)
public class CafePostController {

    final CafePostService cafePostService;

    public CafePostController(CafePostService cafePostService) {
        this.cafePostService = cafePostService;
    }

    @GetMapping("/write")
    String postViewWrite() {
        return PostView.POST_VIEW_WRITE;
    }

    @PostMapping("/write")
    String writePost (@NonNull Post newPost) {
        if(cafePostService.writePost(newPost)) {
            return PostRedirect.POST_REDIRECT_LIST;
        }
        return PostRedirect.POST_REDIRECT_WRITE_FAIL;
    }

    @GetMapping("/list")
    String getPostList(Model model) {
        List<Post> postList = cafePostService.getPostList();
        model.addAttribute("postList", postList);
        model.addAttribute("postCnt", CollectionHelper.getItemNumberOfList(postList));
        return PostView.POST_VIEW_LIST;
    }

    @GetMapping("/content/{postId}")
    String getPostContent(Model model, @NonNull @PathVariable("postId") int postId) {
        Post post = cafePostService.getPostContent(postId);
        model.addAttribute("post", post);
        return PostView.POST_VIEW_CONTENT;
    }
}
