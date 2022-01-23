package com.kakao.cafe.controller;


import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.form.PostCreationForm;
import com.kakao.cafe.form.PostEditForm;
import com.kakao.cafe.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/post")
    public String getPostForm(Model model,HttpSession session){
        logger.info("GET /post : 글쓰기");
        Object value = session.getAttribute("sessionedUser");
        if(value!=null){
            return "post/form";
        }
        return "user/login-error";
    }

    @PostMapping("/post")
    public String createPost(HttpSession session, PostCreationForm form){
        logger.info("POST /post : {} 생성", form.getTitle());
        Object value = session.getAttribute("sessionedUser");
        User user = (User)value;
        Post post = new Post();
        post.setWriter(user.getNickname());
        post.setTitle(form.getTitle());
        post.setContent(form.getContent());
        post.setCreateddate(LocalDate.now());
        postService.create(post);
        return "redirect:/";

    }

    @GetMapping("/")
    public String getPosts(Model model){
        logger.info("GET / : 게시글 전체목록 조회");
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/post/{ID}")
    public String getPost(@PathVariable int ID, Model model){
        logger.info("GET /users/{} : {} 게시글 조회",ID,ID);
        Optional<Post> post = postService.findOne(ID);
        try{
            Post p = post.get();
            model.addAttribute("post",p);
        } catch(Exception e){
            logger.info("null post 에러");
            return "user/unknown-post";
        }

        return "/post/show";
    }

    @GetMapping("/post/{ID}/update")
    public String getPostEditForm(@PathVariable int ID, Model model,HttpSession session){
        logger.info("GET /users/{}/form : {} 게시글 정보 수정",ID,ID);
        Optional<Post> post = postService.findOne(ID);
        Object value = session.getAttribute("sessionedUser");
        User user = (User)value;
        try{
            Post p = post.get();
            if(p.getWriter().equals(user.getNickname())){
                return "user/unkown-user";
            }
            model.addAttribute("post",p);
            return "/post/updateForm";
        } catch(Exception e){
            logger.info("null user 에러");
            return "user/unknown-user";
        }
    }

    @PutMapping("/post/{ID}/update")
    public String postEdit(@PathVariable int ID, PostEditForm form){
        logger.info("POST /users/{}/update : {} 게시글 정보 수정",ID,ID);
        Optional<Post> post = postService.findOne(ID);
        try{
            Post p = post.get();
            p.setTitle(form.getTitle());
            p.setContent(form.getContent());
            postService.edit(p);
            return "index";
        } catch (Exception e){
            logger.info("null user 에러");
            return "user/unknown-user";
        }
    }

    @PutMapping("post/{ID}/delete")
    public String deletePost(@PathVariable int ID, HttpSession session){
        logger.info("GET /users/{}/form : {} 게시글 삭제",ID,ID);
        Optional<Post> post = postService.findOne(ID);
        Object value = session.getAttribute("sessionedUser");
        User user = (User)value;
        try{
            Post p = post.get();
            if(p.getWriter().equals(user.getNickname())){
                return "user/unkown-user";
            }
            postService.delete(p);
            return "index";
        } catch(Exception e){
            logger.info("null user 에러");
            return "user/unknown-user";
        }
    }
}
