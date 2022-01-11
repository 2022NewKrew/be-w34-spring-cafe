package com.kakao.cafe.posts;


import com.kakao.cafe.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Objects;

@RestController
public class PostController {
    private ArrayList<Post> posts = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/questions")
    public String createPost(String title, String writer, int year, int month, int day, int count){
        logger.info("POST /questions : {} 생성", title);
        Post post = new Post(title, writer, year, month, day, count, posts.size()+1);
        posts.add(post);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getPosts(Model model){
        logger.info("GET / : 게시글 전체목록 조회");
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/articles/{index}")
    public String getPost(int ID, Model model){
        logger.info("GET /users/{} : {} 게시글 조회",ID,posts.get(ID).getTitle());
        Post post = null;
        for(Post p : posts){
            if(Objects.equals(p.getID(), ID)){
                post = p;
            }
        }
        model.addAttribute("post", post);
        return "post/show";
    }
}
