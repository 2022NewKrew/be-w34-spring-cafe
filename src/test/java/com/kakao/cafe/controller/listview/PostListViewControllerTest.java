package com.kakao.cafe.controller.listview;

import com.kakao.cafe.constant.PageSize;
import com.kakao.cafe.dto.post.SimplePostInfo;
import com.kakao.cafe.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PostListViewControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostListViewController postListViewController;

    private ModelAndPageView mapv;

    @BeforeEach
    void setUp() {
        mapv = mock(ModelAndPageView.class);
    }

    @Test
    @DisplayName("게시글 리스트 화면 반환 -> 정상, check mav")
    void postListView_checkMav() {
        //Given
        int numOfPost = 1245;
        given(postService.countAll()).willReturn(numOfPost);

        Integer pageNum = 1;
        List<SimplePostInfo> postInfoList = Collections.emptyList();
        given(postService.getListOfSimplePostInfo(pageNum, PageSize.POST_LIST_SIZE)).willReturn(postInfoList);

        //When
        postListViewController.postListView(pageNum, mapv);

        //Then
        then(mapv).should(times(1)).addObject("numOfElement", numOfPost);
        then(mapv).should(times(1)).addObject("elementInfos", postInfoList);
        then(mapv).should(times(1)).setPageNumbers(pageNum, numOfPost / PageSize.POST_LIST_SIZE + 1);
        then(mapv).should(times(1)).setViewName("postList");
    }
}
