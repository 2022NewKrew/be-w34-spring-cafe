package com.kakao.cafe.index.presentation;

import com.kakao.cafe.post.application.SearchPostService;
import com.kakao.cafe.post.presentation.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final SearchPostService postInfoService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    public String getIndexPage(Model model){
        List<PostDto> postDtos = postInfoService.getPosts(1)
                .stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(toList());

        model.addAttribute("posts", postDtos);
        return "index";
    }
}
