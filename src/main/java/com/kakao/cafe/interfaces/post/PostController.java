package com.kakao.cafe.interfaces.post;

import com.kakao.cafe.application.PostService;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.common.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    /**
     * {@link Post} 목록
     * @param model
     */
    @GetMapping
    public String postList(Model model) {
        model.addAttribute("posts", postService.findAllPost());
        return "post_list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post_content";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id, HttpSession session, RedirectAttributes redirectAttrs) {
        Post post = postService.findById(id);
        User user = (User)session.getAttribute("loginUser");
        if (!post.getWriter().equals(user.getUserId())) {
            redirectAttrs.addFlashAttribute("flashMessage", "내가 쓴 글만 삭제할 수 있습니다");
            return "redirect:/posts";
        }

        postService.deleteById(id);
        return "redirect:/posts";
    }

    @PostMapping("/new")
    public String newPost(PostDto postDto) {
        postService.write(postDto);
        return "redirect:/posts";
    }
}
