package com.kakao.cafe.interfaces.post;

import com.kakao.cafe.interfaces.common.PostDto;
import com.kakao.cafe.domain.post.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * {@link PostDto} 목록
     * @param model
     */
    @GetMapping()
    public String postList(Model model) {
        List<PostDto> postDtos = postRepository.findAll();
        model.addAttribute("posts", postDtos);
        return "post_list";
    }

//    /**
//     * 새 {@link Post} 등록할 수 있는 페이지로 이동
//     */
//    @GetMapping("/new")
//    public String newPost() {
//        return "post_new";
//    }

//    @PostMapping("/new")
//    public String newPost(String title, String body) {
//        return "redirect:/post_list";
//    }
}
