package com.kakao.cafe.controller;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView postListView(@RequestParam(defaultValue = "1") Integer pageNum, ModelAndPageView mapv) {
        int numOfPost = postService.countAll();
        mapv.addObject("numOfPost", numOfPost);

        List<SimplePostInfo> postInfos = postService.getListOfSimplePostInfo(pageNum, PageSize.POST_LIST_SIZE);
        mapv.addObject("postInfos", postInfos);

        Integer totalPageNum = numOfPost / PageSize.POST_LIST_SIZE + 1;
        mapv.setPageNumbers(pageNum, totalPageNum);

        mapv.setViewName("postList");

        return mapv;
    }

    @GetMapping("/posts/{postId}")
    public ModelAndView postDetailView(@PathVariable("postId") Long postId, ModelAndView mav) {
        PostViewDto postViewDto = postService.findPostViewDtoById(postId);
        mav.addObject("postViewDto", postViewDto);

        mav.setViewName("postDetail");

        return mav;
    }
}
