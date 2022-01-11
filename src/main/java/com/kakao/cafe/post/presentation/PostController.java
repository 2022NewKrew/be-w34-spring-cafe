package com.kakao.cafe.post.presentation;

import com.kakao.cafe.post.application.CommentService;
import com.kakao.cafe.post.application.PostInfoService;
import com.kakao.cafe.post.application.WritePostService;
import com.kakao.cafe.post.mapper.CommentMapper;
import com.kakao.cafe.post.mapper.PostMapper;
import com.kakao.cafe.post.presentation.dto.CreateCommentRequest;
import com.kakao.cafe.post.presentation.dto.CreatePostRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import lombok.RequiredArgsConstructor;
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

    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @GetMapping("/form")
    public String getPostForm(){
        return "post/form";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model){
        PostDetailDto postDto = postMapper.toPostDetailDto(postInfoService.getPost(id));

        model.addAttribute("post", postDto);
        return "post/info";
    }

    @PostMapping("")
    public String createPost(CreatePostRequest createPostRequest){
        writePostService.save(postMapper.toEntity(createPostRequest));

        return "redirect:";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, CreateCommentRequest commentRequest){
        commentService.addComment(id, commentMapper.toEntity(commentRequest));

        return "redirect:";
    }
}
