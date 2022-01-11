package com.kakao.cafe.Controller;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.model.PostModel;
import com.kakao.cafe.repository.PostRepository;
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

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public String postList(Model model) {
        Posts posts = postRepository.findAll();
        List<PostModel> postModelList = posts.getPosts().stream()
                .map(PostModel::fromPost)
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
        postRepository.insert(new Post(postModel));
        return "redirect:/posts";
    }

    @GetMapping("/post/{idx}")
    public String getPost(@PathVariable int idx, Model model) {
        PostModel postModel = PostModel.fromPost(postRepository.findById(idx));
        model.addAttribute("post", postModel);
        return "qna/show";
    }

    @GetMapping("/posts/deleteAll")
    public String deleteAll() {
        postRepository.deleteAll();
        return "redirect:/posts";
    }

}
