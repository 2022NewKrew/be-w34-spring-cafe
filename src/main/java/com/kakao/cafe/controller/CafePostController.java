package com.kakao.cafe.controller;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafePostService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class CafePostController {
    final CafePostService cafePostService;
    public CafePostController(CafePostService cafePostService) {
        this.cafePostService = cafePostService;
    }

    private static final String POST_DIRECTORY = "post";
    private static final String POST_VIEW_LIST = POST_DIRECTORY+"/list";
    private static final String POST_VIEW_WRITE = POST_DIRECTORY+"/form";
    private static final String POST_VIEW_CONTENT = POST_DIRECTORY+"/show";
    private static final String POST_VIEW_EDIT = POST_DIRECTORY+"/form_edit";

    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String POST_REDIRECT_LIST = REDIRECT_PREFIX+"/posts/list";
    private static final String POST_REDIRECT_WRITE_FAIL = REDIRECT_PREFIX+"/posts/write/fail";
    private static final String POST_REDIRECT_NON_USER = REDIRECT_PREFIX+"/users/sign-in";
    private static final String POST_REDIRECT_EDIT_SUCCESS = REDIRECT_PREFIX+"/posts/list";
    private static final String POST_REDIRECT_EDIT_FAIL = REDIRECT_PREFIX+"/posts/edit/fail";
    private static final String POST_REDIRECT_DELETE_SUCCESS = REDIRECT_PREFIX+"/posts/list";
    private static final String POST_REDIRECT_DELETE_FAIL = REDIRECT_PREFIX+"/posts/edit/fail";

    @GetMapping("/write")
    String postViewWrite(HttpSession httpSession) {
        if( httpSession.getAttribute("signInUser") == null) {
            return POST_REDIRECT_NON_USER;
        }
        return POST_VIEW_WRITE;
    }

    @PostMapping("/write")
    String writePost (HttpSession httpSession, @NonNull Post newPost) {
        Object signInUser = httpSession.getAttribute("signInUser");
        if( signInUser != null ) {
            String userId = ((User)signInUser).getUserId();
            newPost.setUserId(userId);

            if(cafePostService.writePost(newPost)) {
                return POST_REDIRECT_LIST;
            }
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
    String getPostContent(Model model, HttpSession httpSession, @NonNull @PathVariable("postId") int postId) {
        User loginUser = (User) httpSession.getAttribute("signInUser");
        if( loginUser != null ) {
            Post post = cafePostService.getPostContent(postId);
            model.addAttribute("post", post);

            boolean canEdit = loginUser.getUserId().equals(post.getUserId());
            model.addAttribute("canEdit", canEdit);
        }
        return POST_VIEW_CONTENT;
    }

    @GetMapping("/edit/{postId}")
    String postViewEdit(Model model, HttpSession httpSession, @PathVariable("postId") int postId) {
        User loginUser = (User) httpSession.getAttribute("signInUser");
        if( loginUser != null ) {
            Post post = cafePostService.postViewEdit(postId);
            if( post != null && loginUser.getUserId().equals(post.getUserId()) ) {
                model.addAttribute("post", post);
                return POST_VIEW_EDIT;
            }
        }
       return POST_REDIRECT_EDIT_FAIL;
    }
    @PostMapping("/edit/{postId}")
    String editPost (HttpSession httpSession, @NonNull @PathVariable("postId") int postId, @NonNull Post post) {
        User loginUser = (User) httpSession.getAttribute("signInUser");
        if( loginUser != null ) {
            String userId = loginUser.getUserId();
            if (cafePostService.editPost(userId, postId, post)) {
                return POST_REDIRECT_EDIT_SUCCESS;
            }
        }
        return POST_REDIRECT_EDIT_FAIL;
    }
    @DeleteMapping("/delete/{postId}")
    String deletePost(HttpSession httpSession, @NonNull @PathVariable("postId") int postId) {
        User loginUser = (User) httpSession.getAttribute("signInUser");
        if(loginUser != null) {
            String userId = loginUser.getUserId();
            if(cafePostService.deletePost(postId, userId)) {
                return POST_REDIRECT_DELETE_SUCCESS;
            }
        }
        return POST_REDIRECT_DELETE_FAIL;
    }
}
