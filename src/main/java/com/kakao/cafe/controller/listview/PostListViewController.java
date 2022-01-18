package com.kakao.cafe.controller.listview;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostListViewController extends ListViewController<SimplePostInfo> {

    private final PostService postService;

    @GetMapping("/")
    public ModelAndView postListView(@RequestParam(defaultValue = "1") Integer pageNum, ModelAndPageView mapv) {
        return listView(pageNum, mapv);
    }

    @Override
    protected int getNumOfElement() {
        return postService.countAll();
    }

    @Override
    protected int getPageSize() {
        return PageSize.POST_LIST_SIZE + 1;
    }

    @Override
    protected List<SimplePostInfo> getSimpleElementInfos(Integer pageNum, Integer pageSize) {
        return postService.getListOfSimplePostInfo(pageNum, pageSize);
    }

    @Override
    protected String getListViewName() {
        return "postList";
    }
}
