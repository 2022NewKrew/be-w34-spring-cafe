package com.kakao.cafe.Controller;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    UserController userController;

    private final Posts posts = new Posts();

    @GetMapping("/posts")
    public String postList(Model model) {
        List<PostModel> postModelList = posts.getPosts().stream()
                        .map(p -> PostModel.fromPost(p))
                        .collect(Collectors.toList());
        model.addAttribute("posts", postModelList);
        return "qna/posts";
    }

    @GetMapping("/questions")
    public String question(){
        return "qna/form";
    }

    @PostMapping("/post")
    public String makePost(PostModel postModel){
        Users users = userController.getUsers();
        posts.add(new Post(postModel, users));
        return "redirect:/posts";
    }

    @GetMapping("/post/{idx}")
    public String getPost(@PathVariable int idx, Model model){
        PostModel postModel = PostModel.fromPost(posts.get(idx));
        model.addAttribute("post", postModel);
        return "qna/show";
    }
}
