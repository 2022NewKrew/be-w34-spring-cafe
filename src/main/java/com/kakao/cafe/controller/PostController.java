package com.kakao.cafe.controller;


import com.kakao.cafe.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class PostController {
    private ArrayList<Post> posts = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/questions")
    public String getPostForm(Model model){
        logger.info("GET /questions : 글쓰기");
        return "post/form";
    }

    @PostMapping("/questions")
    public String createPost(String title, String writer, LocalDate createdDate){
        logger.info("POST /questions : {} 생성", title);
        Post post = new Post(title, writer, createdDate, posts.size()+1);
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
            if(Objects.equals(p.getPostId(), ID)){
                post = p;
            }
        }
        model.addAttribute("post", post);
        return "post/show";
    }
}
