package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.Reply;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.CafePostService;
import com.kakao.cafe.service.CafeReplyService;
import com.kakao.cafe.vo.PostVo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class CafePostController {
    final CafePostService cafePostService;
    final CafeReplyService cafeReplyService;

    public CafePostController(CafePostService cafePostService, CafeReplyService cafeReplyService) {
        this.cafePostService = cafePostService;
        this.cafeReplyService = cafeReplyService;
    }

    private final static String SIGN_IN_USER = "signInUser";

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
    String postViewWrite(@LoginUser String loginUser) {
        if(loginUser == null) {
            return POST_REDIRECT_NON_USER;
        }
        return POST_VIEW_WRITE;
    }

    @PostMapping("/write")
    String writePost (@LoginUser String loginUser, @NonNull Post newPost) {
        if( loginUser != null ) {
            newPost.setUserId(loginUser);
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
    String getPostContent(Model model, @LoginUser String loginUser, @NonNull @PathVariable("postId") int postId) {
        if( loginUser != null ) {
            Post post = cafePostService.getPostContent(postId);
            if( post != null ) {
                List<Reply> replyList = cafeReplyService.getReplyList(postId);

                PostVo postVo = new PostVo(post, replyList, loginUser);
                model.addAttribute("postVo", postVo);
            }
        }
        return POST_VIEW_CONTENT;
    }

    @GetMapping("/edit/{postId}")
    String postViewEdit(Model model, @LoginUser String loginUser, @PathVariable("postId") int postId) {
        if( loginUser != null ) {
            Post post = cafePostService.postViewEdit(postId);
            if( post != null && loginUser.equals(post.getUserId()) ) {
                model.addAttribute("post", post);
                return POST_VIEW_EDIT;
            }
        }
       return POST_REDIRECT_EDIT_FAIL;
    }

    @PostMapping("/edit/{postId}")
    String editPost (@LoginUser String loginUser, @NonNull @PathVariable("postId") int postId, @NonNull Post post) {
        if( loginUser != null && cafePostService.editPost(loginUser, postId, post)) {
            return POST_REDIRECT_EDIT_SUCCESS;
        }
        return POST_REDIRECT_EDIT_FAIL;
    }

    @DeleteMapping("/delete/{postId}")
    String deletePost(@LoginUser String loginUser, @NonNull @PathVariable("postId") int postId) {
        if(loginUser != null && cafePostService.deletePost(postId, loginUser)) {
            return POST_REDIRECT_DELETE_SUCCESS;
        }
        return POST_REDIRECT_DELETE_FAIL;
    }
}
