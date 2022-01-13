package com.kakao.cafe.interfaces.post;

import com.kakao.cafe.application.PostService;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.interfaces.common.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

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

    @PostMapping("/new")
    public String newPost(PostDto postDto) {
        postService.write(postDto);
        return "redirect:/posts";
    }
}
