package com.kakao.cafe.post.presentation;

import com.kakao.cafe.post.application.CommentService;
import com.kakao.cafe.post.application.PostInfoService;
import com.kakao.cafe.post.application.WritePostService;
import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.CommentRequest;
import com.kakao.cafe.post.presentation.dto.PostRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostInfoService postInfoService;
    private final WritePostService writePostService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @GetMapping("/form")
    public String getPostForm(){
        return "post/form";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model){
        PostDetailDto postDto = modelMapper.map(postInfoService.getPost(id), PostDetailDto.class);

        model.addAttribute("post", postDto);
        return "post/info";
    }

    @PostMapping("")
    public String createPost(PostRequest createPostRequest){
        writePostService.save(modelMapper.map(createPostRequest, Post.class));

        return "redirect:";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, CommentRequest commentRequest){
        commentService.addComment(id, modelMapper.map(commentRequest, Comment.class));

        return "redirect:";
    }
}
