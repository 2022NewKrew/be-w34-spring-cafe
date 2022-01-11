package com.kakao.cafe.post.presentation;

import com.kakao.cafe.post.application.PostInfoService;
import com.kakao.cafe.post.application.WritePostService;
import com.kakao.cafe.post.mapper.PostMapper;
import com.kakao.cafe.post.presentation.dto.CreatePostRequest;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import com.kakao.cafe.post.presentation.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostInfoService postInfoService;
    private final WritePostService writePostService;

    @GetMapping("/form")
    public String getPostForm(){
        return "post/form";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model){
        PostDetailDto postDto = PostMapper.toPostDetailDto(postInfoService.getPost(id));

        model.addAttribute("post", postDto);
        return "post/info";
    }

    @PostMapping("")
    public String createPost(CreatePostRequest createPostRequest){
        writePostService.save(PostMapper.toEntity(createPostRequest));

        return "redirect:";
    }
}
